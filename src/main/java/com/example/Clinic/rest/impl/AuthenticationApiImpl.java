package com.example.Clinic.rest.impl;

import com.example.Clinic.model.User;
import com.example.Clinic.rest.AuthenticationApi;
import com.example.Clinic.rest.support.dto.JwtAuthenticationRequest;
import com.example.Clinic.rest.support.dto.UserTokenState;
import com.example.Clinic.security.services.UserDetailsImpl;
import com.example.Clinic.security.token.TokenUtils;
import com.example.Clinic.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationApiImpl implements AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public ResponseEntity<UserTokenState> createAuthenticationToken(JwtAuthenticationRequest authenticationRequest,
                                                                    HttpServletResponse response) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(404).build();
        }catch (DisabledException ex ){
            return ResponseEntity.status(403).build();
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String jwt = tokenUtils.generateToken(user.getUsername(), roles.toString());
        String refreshJwt = tokenUtils.generateRefreshToken(jwt);

        return ResponseEntity.ok(new UserTokenState(jwt, refreshJwt));


    }

    @Override
    public ResponseEntity refreshAuthenticationToken(HttpServletRequest request) {


        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        UserDetailsImpl user = (UserDetailsImpl) this.userDetailsService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);


            return ResponseEntity.ok(refreshedToken);
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }


}
