package org.example.recrutement.filters;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.recrutement.services.impl.MyUserDetailsService;
import org.example.recrutement.utils.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;


import java.io.IOException;

@Component
public class JWTrequestfilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtility jwtUtil;
    @Autowired
    private MyUserDetailsService msd;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Ignorer les endpoints publics
        String path = request.getServletPath();
        if (path.equals("/login") || path.equals("register")|| path.equals("/forgot-password") || path.equals("/reset-password")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Récupération du header Authorization
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.getUsernameFromToken(jwt);
        }
        // Validation du JWT
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails ud = msd.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, ud)) {
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(ud, null,
                        ud.getAuthorities());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        }
        filterChain.doFilter (

                request,response);
    }
}
