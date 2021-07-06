package com.example.Clinic.security.config;

import com.example.Clinic.security.auth.AuthenticationUsernamePasswordFilter;
import com.example.Clinic.security.auth.RestAuthenticationEntryPoint;
import com.example.Clinic.security.token.TokenAuthenticationFilter;
import com.example.Clinic.security.token.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;


    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {

        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationUsernamePasswordFilter authenticationTokenFilterBean()
            throws Exception {
        AuthenticationUsernamePasswordFilter authenticationFilter = new AuthenticationUsernamePasswordFilter();
        authenticationFilter
                .setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel().anyRequest().requiresSecure();
        http.cors().configurationSource(corsConfigurationSource());
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/passwordless").permitAll()
                .antMatchers("/auth/magic/{token}").permitAll()
                .antMatchers("/auth/linkLogin/{token}").permitAll()
                .antMatchers("/user/enable").permitAll()
                .antMatchers(HttpMethod.POST, "/patients").permitAll()
                .anyRequest().authenticated();


            http.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userDetailsService),
                BasicAuthenticationFilter.class);



    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        CorsConfiguration defaultConfiguration = new CorsConfiguration();
        defaultConfiguration.applyPermitDefaultValues();

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        configuration = configuration.combine(defaultConfiguration);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




}
