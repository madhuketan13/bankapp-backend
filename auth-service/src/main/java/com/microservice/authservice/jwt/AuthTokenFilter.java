package com.microservice.authservice.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microservice.authservice.security.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);

    private final JwtUtils jwtUtils;

    private final  CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {

            String jwt = parseJwt(request);
            LOGGER.error("AuthTokenFilter | doFilterInternal | jwt: {}", jwt);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            LOGGER.error("AuthTokenFilter | doFilterInternal | Cannot set user authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }


    private String parseJwt(HttpServletRequest request) {

        String headerAuth = request.getHeader("Authorization");

        LOGGER.info("AuthTokenFilter | parseJwt | headerAuth: {}", headerAuth);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {

            LOGGER.info("AuthTokenFilter | parseJwt | parseJwt: {}", headerAuth.substring(7, headerAuth.length()));

            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
