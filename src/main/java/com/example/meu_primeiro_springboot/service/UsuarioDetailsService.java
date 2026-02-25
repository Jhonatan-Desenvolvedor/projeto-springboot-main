package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementação de UserDetailsService para Spring Security.
 * Carrega detalhes do usuário pelo username, usado na autenticação JWT.
 * Comunica-se com UsuarioRepository para buscar usuários e é injetado no SecurityConfig.
 */
@Service
public class UsuarioDetailsService  implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carrega detalhes do usuário pelo username para Spring Security.
     * Busca o usuário via UsuarioRepository, lança UsernameNotFoundException se não encontrado,
     * e constrói um UserDetails com papel "USER".
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(("usuario não encontrado")));


        return User.builder()
                .username(usuario.getUsername())
                        .password(usuario.getPassword())
                        .roles("USER")
                        .build();
    }
}
