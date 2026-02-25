package com.example.meu_primeiro_springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.meu_primeiro_springboot.model.Veiculo;
import com.example.meu_primeiro_springboot.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin("*") // Garante que o Angular consiga acessar sem erro de CORS
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar() {
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    // ESTE É O MÉTODO QUE ESTAVA FALTANDO! 
    // Ele responde ao GET do Angular para preencher o select de veículos
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Veiculo>> listarPorCliente(@PathVariable Long clienteId) {
        List<Veiculo> veiculos = veiculoService.buscarPorCliente(clienteId);
        return ResponseEntity.ok(veiculos);
    }

    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Veiculo> criar(@PathVariable Long clienteId, @RequestBody Veiculo veiculo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veiculoService.salvar(clienteId, veiculo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        veiculoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}