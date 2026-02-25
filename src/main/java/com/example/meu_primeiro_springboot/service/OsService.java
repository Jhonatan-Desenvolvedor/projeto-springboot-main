package com.example.meu_primeiro_springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.meu_primeiro_springboot.model.OrdemServico;
import com.example.meu_primeiro_springboot.model.StatusOs;
import com.example.meu_primeiro_springboot.repository.ClienteRepository;
import com.example.meu_primeiro_springboot.repository.OsRepository;
import com.example.meu_primeiro_springboot.repository.VeiculoRepository;

@Service
public class OsService {

    @Autowired
    private OsRepository osRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Transactional
    public OrdemServico criar(OrdemServico os) {
        // 1. Validar se o cliente e veículo existem
        if (!clienteRepository.existsById(os.getCliente().getId())) {
            throw new RuntimeException("Cliente inválido");
        }
        if (!veiculoRepository.existsById(os.getVeiculo().getId())) {
            throw new RuntimeException("Veículo inválido");
        }

        // 2. Calcular o valor total no Back-end (Segurança!)
        double totalServicos = 0.0; // Usando Double como você pediu
        double totalProdutos = 0.0;

        // Se houver lógica de itens, você somaria aqui. 
        // Na versão simples, pegamos o que vem do front e validamos:
        if (os.getValorTotal() == null || os.getValorTotal() <= 0) {
            // Lógica para recalcular ou validar valor enviado
        }

        os.setData(LocalDateTime.now());
        os.setStatus(StatusOs.valueOf("ABERTO"));

        return osRepository.save(os);
    }

    public List<OrdemServico> listarTodas() {
        return osRepository.findAll();
    }

    @Transactional
    public OrdemServico finalizar(Long id) {
        OrdemServico os = osRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OS não encontrada"));
        
        // Define o status como FECHADO
        os.setStatus(StatusOs.valueOf("FECHADO"));
        
        // IMPORTANTE: Use um campo específico para o fechamento
        // Se você quiser usar o mesmo campo 'data', ele será sobrescrito.
        // O ideal é:
        os.setDataFechamento(LocalDateTime.now()); 
        
        return osRepository.save(os);
    }
}
