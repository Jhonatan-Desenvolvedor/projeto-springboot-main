package com.example.meu_primeiro_springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.meu_primeiro_springboot.model.Servico;
import com.example.meu_primeiro_springboot.repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    @Transactional
    public Servico salvar(Servico servico) {
        // Validação de segurança: preço não pode ser nulo ou negativo
        if (servico.getValorServico() == null || servico.getValorServico() < 0) {
            throw new RuntimeException("Preço do serviço inválido");
        }
        return servicoRepository.save(servico);
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    @Transactional
    public void excluir(Long id) {
        servicoRepository.deleteById(id);
    }
}