package com.example.meu_primeiro_springboot.repository;

import com.example.meu_primeiro_springboot.model.Veiculo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Produto.
 * Fornece métodos CRUD padrão via JpaRepository e é injetado nos serviços para acesso ao banco de dados.
 * Comunica-se com o banco MySQL configurado no application.properties.
 */
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	
Optional<Veiculo> findByPlaca(String placa);
    

    
    @Query("SELECT v FROM Veiculo v WHERE v.cliente.id = :clienteId")
    List<Veiculo> findByClienteId(@Param("clienteId") Long clienteId);
}

