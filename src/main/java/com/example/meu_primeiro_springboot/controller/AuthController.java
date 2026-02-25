package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.security.JwtUtil;
import com.example.meu_primeiro_springboot.service.UsuarioService;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Map;
import java.util.Optional;

/**
 * Controlador REST para autenticação de usuários.
 * Gerencia endpoints de registro e login, utilizando JWT para autenticação.
 * Comunica-se com UsuarioService para operações de usuário e JwtUtil para geração de tokens.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://oficinajbt.netlify.app", allowCredentials = "true")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para registro de novos usuários.
     * Recebe username e password no corpo da requisição, registra o usuário via UsuarioService
     * e retorna o usuário criado.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // opcional: verificar se usuário já existe
        if (usuarioService.buscarPorUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Usuário já existe"));
        }

        Usuario usuario = usuarioService.registrarUsuario(username, password);
        return ResponseEntity.ok(Map.of(
                "id", usuario.getId(),
                "username", usuario.getUsername()
        ));
    }

    /**
     * Endpoint para login de usuários.
     * Recebe username e password, verifica credenciais via UsuarioService,
     * gera um token JWT se válido e retorna-o; caso contrário, retorna erro 401.
     */
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorUsername(request.get("username"));

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // verifica senha com BCrypt
            if (usuarioService.getPasswordEncoder().matches(request.get("password"), usuario.getPassword())) {
                String token = JwtUtil.generateToken(usuario.getUsername());
                return ResponseEntity.ok(Map.of("token", token));
            }
        }

        return ResponseEntity.status(401).body(Map.of("error", "Credenciais inválidas"));
    }
}
