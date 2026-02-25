package com.example.meu_primeiro_springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.meu_primeiro_springboot.model.Cliente;
import com.example.meu_primeiro_springboot.model.Veiculo;
import com.example.meu_primeiro_springboot.repository.ClienteRepository;
import com.example.meu_primeiro_springboot.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }
    
 // ADICIONE ESTE MÉTODO AQUI:
    public List<Veiculo> buscarPorCliente(Long clienteId) {
        return veiculoRepository.findByClienteId(clienteId);
    }

    @Transactional
    public Veiculo salvar(Long clienteId, Veiculo veiculo) {
        // Busca o cliente e valida existência
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Faz o vínculo (Pai -> Filho)
        veiculo.setCliente(cliente);
        
        return veiculoRepository.save(veiculo);
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado");
        }
        veiculoRepository.deleteById(id);
    }
}
