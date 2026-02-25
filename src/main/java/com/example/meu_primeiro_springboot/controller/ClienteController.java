package com.example.meu_primeiro_springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.meu_primeiro_springboot.model.Cliente;
import com.example.meu_primeiro_springboot.model.Veiculo;
import com.example.meu_primeiro_springboot.repository.ClienteRepository;
import com.example.meu_primeiro_springboot.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "https://oficinajbt.netlify.app", allowCredentials = "true")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ClienteRepository repository;

    // O ERRO ESTÁ AQUI: Certifique-se de que é @PutMapping e tem o /{id}
    @PutMapping("/{id}") 
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        return repository.findById(id)
            .map(record -> {
                record.setNome(cliente.getNome());
                record.setEndereco(cliente.getEndereco());
                record.setTelefone(cliente.getTelefone());
                
                // Como você usa CascadeType.ALL e orphanRemoval, 
                // atualizar a lista aqui sincroniza os veículos
                record.getVeiculos().clear();
                record.getVeiculos().addAll(cliente.getVeiculos());
                // Garante que cada veículo saiba quem é seu dono
                record.getVeiculos().forEach(v -> v.setCliente(record));

                Cliente updated = repository.save(record);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
