package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Produto.
 * Fornece métodos CRUD padrão via JpaRepository e é injetado nos serviços para acesso ao banco de dados.
 * Comunica-se com o banco MySQL configurado no application.properties.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	// Busca opcional para validar se o cliente já existe antes de salvar
    Optional<Cliente> findByNome(String nome);

}