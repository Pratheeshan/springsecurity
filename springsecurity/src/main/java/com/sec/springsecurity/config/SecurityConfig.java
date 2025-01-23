package com.sec.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration(proxyBeanMethods = true)
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/api/v1/auth/*").permitAll()//Endpoints starting with /api/v1/auth/* are publicly accessible.
                                .anyRequest().authenticated())//All other requests require the user to be authenticated.
                //Ensures the JWT-based authentication process happens before username/password-based authentication.
                .addFilterBefore(new AuthenticationFilter(jwtUtil, userDetailsServiceImpl), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    //Configures a password encoder using the BCrypt hashing algorithm
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Responsible for authenticating users.
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
