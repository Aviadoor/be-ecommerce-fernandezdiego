package com.ecommerce.backend.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class UtilidadJwt {

    @Value("${seguridad.jwt.clave}")
    private String claveSecreta;

    @Value("${seguridad.jwt.expiracion-ms}")
    private long tiempoExpiracion;

    private Key obtenerLlave() {
        return Keys.hmacShaKeyFor(claveSecreta.getBytes(StandardCharsets.UTF_8));
    }

    public String emitirToken(String username) {
        Date ahora = new Date();
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(ahora)
            .setExpiration(new Date(ahora.getTime() + tiempoExpiracion))
            .signWith(obtenerLlave())
            .compact();
    }

    public String obtenerUsuarioDeToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(obtenerLlave())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}