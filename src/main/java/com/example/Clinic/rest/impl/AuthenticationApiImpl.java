package com.example.Clinic.rest.impl;

import com.example.Clinic.model.*;
import com.example.Clinic.rest.AuthenticationApi;
import com.example.Clinic.rest.support.dto.JwtAuthenticationRequest;
import com.example.Clinic.rest.support.dto.PasswordLessDto;
import com.example.Clinic.rest.support.dto.UserTokenState;
import com.example.Clinic.security.services.UserDetailsImpl;
import com.example.Clinic.security.token.TokenUtils;
import com.example.Clinic.service.PatientService;
import com.example.Clinic.service.impl.EmailServiceImpl;
import com.example.Clinic.service.impl.UserDetailsServiceImpl;
import com.example.Clinic.service.impl.UserServiceImpl;
import org.json.*;
import org.apache.tomcat.util.json.JSONParser;

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
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AuthenticationApiImpl implements AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PatientService patientService;

    private List<TokenExpiration> tokenStore = new ArrayList<>();




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
        }
        catch (DisabledException ex ){
            System.out.println("im caught");
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
    public ResponseEntity<String> passwordLessRequest(PasswordLessDto dto, HttpServletResponse response) {
        Optional<User> optionalUser = userService.findUserByEmail(dto.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(user);

            List<String> roles = new ArrayList<>();
            for (Authority one : user.getRoles()) {
                roles.add(one.getName().toString());
            }

            String jwt = tokenUtils.generateToken(user.getEmail(), roles.toString());
            TokenExpiration tokenExpiration = new TokenExpiration();
            tokenExpiration.setToken(jwt);
            tokenExpiration.setTime(LocalDateTime.now());

            emailService.sendEmail(dto.getEmail(), "LOGIN ATTEMPT",
                    "Hello " + user.getName() + " " + user.getLastName() +
                            ", ignore this email if you didn't want to log in. " +
                            "\nIf you did want to login VIA password-less login here is the redirection link." +
                            "\nLink : http://localhost:3000/magic/?token=" + jwt);
            tokenStore.add(tokenExpiration);
            System.out.println(tokenStore);
            return new ResponseEntity<String>("Verification email has been sent", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User associated with this email does not exist", HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<TokenPair> tokenCheck(String token) {
        boolean valid = false;
        List<TokenExpiration> checkedTokenStore = tokenStore;
        for (TokenExpiration t : tokenStore) {
            if (t.getTime().plusMinutes(1).isBefore(LocalDateTime.now())) {
                checkedTokenStore.remove(t);
            }
        }
        for (TokenExpiration t : checkedTokenStore) {
            if (t.getToken().equals(token)) {
                System.out.println(t);
                tokenStore.remove(t);
                valid = true;
                break;
            }
        }
        if (valid) {
            String refreshJwt = tokenUtils.generateRefreshToken(token);
            return ResponseEntity.ok(new TokenPair(token, refreshJwt));
        } else {
            return new ResponseEntity(null, HttpStatus.NO_CONTENT);
        }
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

    @Override
    public ResponseEntity loginViaLink(String token) throws JSONException {
//       Jwt jwt = token.
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

//        JSONParser parser = new JSONParser(payload);
//        JSONObject json = (JSONObject) parser.parse(payload);
        JSONObject obj = new JSONObject(payload);

        String email = obj.getString("sub");
        System.out.println("EMAIL:");
        System.out.println(email);
        User user = userService.findUserByEmail(email).orElse(null);

        Patient patient = patientService.getPatientById(user.getId()).orElse(null);
        System.out.println(patient);
        if(patient.isVisitedMail() == true) {
            System.out.println("ALREADY VISITED");
            return new ResponseEntity("already visited", HttpStatus.BAD_REQUEST);
        }

        else {
            user.setEnabled(true);
            userService.enable(user);
            user.setFirstTime(false);
            patient.setVisitedMail(true);
            patientService.updatePatient(patient, patient.getId());
            System.out.println("NOT VISITED MAIL - ELSE");
            // Jwt jwt = decoder.decode(token);
            String refreshJwt = tokenUtils.generateRefreshToken(token);
            return ResponseEntity.ok(new TokenPair(token, refreshJwt));
        }

    }



}
