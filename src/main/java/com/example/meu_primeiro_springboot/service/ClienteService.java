package com.example.meu_primeiro_springboot.service;


import com.example.meu_primeiro_springboot.model.Cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.meu_primeiro_springboot.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Buscar por ID com tratamento de erro
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
    }

    // Salvar cliente e garantir vínculo com veículos
    @Transactional
    public Cliente salvar(Cliente cliente) {
        if (cliente.getVeiculos() != null) {
            // Garante que cada veículo aponte para este cliente (necessário para JPA)
            cliente.getVeiculos().forEach(veiculo -> veiculo.setCliente(cliente));
        }
        return clienteRepository.save(cliente);
    }

    // Deletar cliente
    @Transactional
    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}