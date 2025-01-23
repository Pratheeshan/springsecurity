package com.sec.springsecurity.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final JwtUtil jwtUtil; //responsible for parsing validating and extracting info from JWT
    private final UserDetailsServiceImpl userDetailsService; //loading userdetail based on the extracted username

    @Autowired
    public AuthenticationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    //main method to handle filtering logic
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest,
                                    @NotNull HttpServletResponse httpServletResponse,
                                    @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String jwt = jwtUtil.parse(httpServletRequest); // Parse JWT from request
            if (jwt != null && jwtUtil.validate(jwt)) { // Validate the token
                String username = jwtUtil.getUsername(jwt);// Extract username

                // Load user details and create an authentication object
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                // Set authentication in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
        }
        // Continue the filter chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
