package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Produto.
 * Fornece métodos CRUD padrão via JpaRepository e é injetado nos serviços para acesso ao banco de dados.
 * Comunica-se com o banco MySQL configurado no application.properties.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	// Busca rápida para o Angular preencher o campo de busca/autocomplete
    List<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto);
}
