package com.management.cni.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
    private MyUserDetailsService userDetailsService;

  @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    	 // Get authorization header and validate
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
          jwt = authorizationHeader.substring(7);
          username = jwtUtil.extractUsername(jwt);
          //   username = jwtTokenManager.getUsernameFromToken(jwt);
        }

      // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // Get jwt token and validate
            if (jwtUtil.validateToken(jwt, userDetails)) {
                // Get user identity and set it on the spring security context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities ());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              // After setting the Authentication in the context, we specify
              // that the current user is authenticated. So it passes the
              // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
	// if the request has JWT, validate it, parse username from it
	//from username, get UserDetails to create an Authentication object
	//set the current UserDetails in SecurityContext using setAuthentication(authentication) method


}
