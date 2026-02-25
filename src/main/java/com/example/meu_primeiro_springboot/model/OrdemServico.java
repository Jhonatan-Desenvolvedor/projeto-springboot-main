package com.example.meu_primeiro_springboot.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class OrdemServico {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamentos Diretos
    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Veiculo veiculo;

    // Listas Simples (O JPA cria as tabelas de junção sozinho)
    @ElementCollection
    private List<String> servicos = new ArrayList<>();

    @ElementCollection
    private List<String> produtos = new ArrayList<>();

    private Double valorTotal;
    @Enumerated(EnumType.STRING)
    private StatusOs status = StatusOs.ABERTO;
    private LocalDateTime data = LocalDateTime.now();
 // Adicione este campo na sua classe OrdemServico
    private LocalDateTime dataFechamento;
    
    
    
	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public List<String> getServicos() {
		return servicos;
	}
	public void setServicos(List<String> servicos) {
		this.servicos = servicos;
	}
	public List<String> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<String> produtos) {
		this.produtos = produtos;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public StatusOs getStatus() {
		return status;
	}
	public void setStatus(StatusOs status) {
		this.status = status;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
}