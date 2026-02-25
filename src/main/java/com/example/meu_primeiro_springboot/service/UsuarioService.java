package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.model.TipoUsuario;
import com.example.meu_primeiro_springboot.model.Usuario;
import com.example.meu_primeiro_springboot.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço para operações de usuário, incluindo registro e busca.
 * Criptografa senhas usando BCrypt e salva usuários no banco via UsuarioRepository.
 * Chamado pelo AuthController para registro e login.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registra um novo usuário.
     * Criptografa a senha com BCrypt, cria um Usuario e salva via UsuarioRepository.
     */
    public Usuario registrarUsuario(String username, String password) {
        String senhaCriptografada = passwordEncoder.encode(password);
        
        // Instancia o usuário e preenche TODOS os campos obrigatórios
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(senhaCriptografada);
        usuario.setTipo(TipoUsuario.ADMINISTRADOR); // <--- AQUI ESTÁ A SOLUÇÃO
        
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca um usuário por username.
     * Chama UsuarioRepository.findByUsername() para localizar o usuário.
     */
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);

    }

}
