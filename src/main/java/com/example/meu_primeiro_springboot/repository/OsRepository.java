package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.OrdemServico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Produto.
 * Fornece métodos CRUD padrão via JpaRepository e é injetado nos serviços para acesso ao banco de dados.
 * Comunica-se com o banco MySQL configurado no application.properties.
 */
@Repository
public interface OsRepository extends JpaRepository<OrdemServico, Long> {
	// Busca todas as ordens de um cliente específico
	// Busca todas as ordens de um cliente específico
    List<OrdemServico> findByClienteId(Long clienteId);

    // REMOVIDO O 'STATIC' E O CORPO DO MÉTODO
    @Query("SELECT SUM(os.valorTotal) FROM OrdemServico os WHERE os.status = 'FECHADO'")
    Double somarTotalOsFechadas();
}
