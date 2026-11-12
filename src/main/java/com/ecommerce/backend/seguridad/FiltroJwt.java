package com.ecommerce.backend.seguridad;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class FiltroJwt extends OncePerRequestFilter {

    private final UtilidadJwt utilidadJwt;
    private final UserDetailsService userDetailsService;

    public FiltroJwt(UtilidadJwt utilidadJwt, UserDetailsService userDetailsService) {
        this.utilidadJwt = utilidadJwt;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String cabecera = req.getHeader("Authorization");
        if (cabecera != null && cabecera.startsWith("Bearer ")) {
            String token = cabecera.substring(7);
            try {
                String username = utilidadJwt.obtenerUsuarioDeToken(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails detalles = userDetailsService.loadUserByUsername(username);
                    var auth = new UsernamePasswordAuthenticationToken(
                        detalles, null, detalles.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ignored) {
                // Token invalido o expirado
            }
        }
        chain.doFilter(req, res);
    }
}