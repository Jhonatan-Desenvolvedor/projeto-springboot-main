package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para a entidade Usuario.
 * Extende JpaRepository para operações CRUD e adiciona método customizado findByUsername.
 * Usado pelos serviços de usuário para autenticação e registro.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);

}
