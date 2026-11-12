package com.ecommerce.backend.controlador;

import com.ecommerce.backend.dto.LoginDto;
import com.ecommerce.backend.seguridad.UtilidadJwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
//Controlador de Autenticacion
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthControlador {

    private final AuthenticationManager authManager;
    private final UtilidadJwt utilidadJwt;

    public AuthControlador(AuthenticationManager authManager, UtilidadJwt utilidadJwt) {
        this.authManager = authManager;
        this.utilidadJwt = utilidadJwt;
    }

    @PostMapping("/login")
    public Map<String, String> iniciarSesion(@RequestBody LoginDto dto) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        String token = utilidadJwt.emitirToken(dto.username());
        return Map.of("token", token);
    }
}