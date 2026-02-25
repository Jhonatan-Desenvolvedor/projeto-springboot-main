package com.example.meu_primeiro_springboot.controller;

import com.example.meu_primeiro_springboot.model.Produto;
import com.example.meu_primeiro_springboot.repository.ProdutoRepository;
import com.example.meu_primeiro_springboot.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações CRUD de produtos.
 * Expõe endpoints para listar, buscar, criar e deletar produtos.
 * Comunica-se com ProdutoService para executar a lógica de negócio.
 */
@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*") 
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produto));
    }
    
    @PutMapping("/{id}") // O {id} PRECISA estar aqui para bater com /api/produtos/1
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        // Log para você ver no console se a requisição chegou
        System.out.println("Atualizando produto ID: " + id);
        
        // No seu service, certifique-se de que você está usando o ID da URL
        return ResponseEntity.ok(produtoService.atualizar(id, produto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}