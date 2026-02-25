package com.example.meu_primeiro_springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.meu_primeiro_springboot.model.OrdemServico;
import com.example.meu_primeiro_springboot.repository.OsRepository;
import com.example.meu_primeiro_springboot.service.OsService;

@RestController
@RequestMapping("/api/ordens-servico")
@CrossOrigin(origins = "*") 

public class OsController {

    @Autowired
    private OsService osService;
    
    @Autowired
    private OsRepository repository;

    @PutMapping("/{id}") // O ID deve estar entre chaves aqui!
    public ResponseEntity<OrdemServico> update(@PathVariable Long id, @RequestBody OrdemServico os) {
        return repository.findById(id)
            .map(record -> {
                record.setStatus(os.getStatus());
                record.setValorTotal(os.getValorTotal());
                record.setServicos(os.getServicos());
                record.setProdutos(os.getProdutos());
                OrdemServico updated = repository.save(record);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdemServico> salvar(@RequestBody OrdemServico os) {
        return ResponseEntity.status(HttpStatus.CREATED).body(osService.criar(os));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {
        return ResponseEntity.ok(osService.listarTodas());
    }
    
    @PostMapping("/faturamento") // O complemento da URL que o Angular está chamando
    public ResponseEntity<?> salvarOrdem(@RequestBody Object ordemData) {
        // Por enquanto, vamos apenas printar para ver se os dados chegam
        System.out.println("Dados recebidos da OS: " + ordemData);
        
        // Aqui você chamaria o service.salvar(ordemData)
        
        return ResponseEntity.ok().body("{\"mensagem\": \"Ordem de serviço salva com sucesso!\"}");
    }
    
    @GetMapping("/faturamento")
    public ResponseEntity<Double> getFaturamentoTotal(
            @RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim) {
        
        List<OrdemServico> ordens = repository.findAll();

        double total = ordens.stream()
                // 1. Filtra apenas ordens com status FECHADO
                .filter(os -> "FECHADO".equalsIgnoreCase(String.valueOf(os.getStatus())))
                // 2. Filtra pelo período da data de fechamento (se as datas forem passadas)
                .filter(os -> {
                    if (inicio == null || fim == null || os.getData() == null) return true;
                    
                    // Pegamos a data da OS (que você atualiza no service.finalizar)
                    String dataOs = os.getData().toString().split("T")[0]; 
                    return dataOs.compareTo(inicio) >= 0 && dataOs.compareTo(fim) <= 0;
                })
                .mapToDouble(os -> os.getValorTotal() != null ? os.getValorTotal() : 0.0)
                .sum();

        return ResponseEntity.ok(total);
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<OrdemServico> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(osService.finalizar(id));
    }
}