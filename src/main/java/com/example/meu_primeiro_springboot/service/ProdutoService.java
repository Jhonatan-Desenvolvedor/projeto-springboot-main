package com.example.meu_primeiro_springboot.service;

import com.example.meu_primeiro_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.meu_primeiro_springboot.model.Produto;
import com.example.meu_primeiro_springboot.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para lógica de negócio relacionada a produtos.
 * Coordena operações CRUD, validando existência e lançando exceções quando necessário.
 * Comunica-se com ProdutoRepository para acesso ao banco e é chamado pelos controladores.
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto salvar(Produto produto) {
        // Validação básica para Double
        if (produto.getPrecoProduto() != null && produto.getPrecoProduto() < 0) {
            throw new RuntimeException("O preço não pode ser negativo");
        }
        return produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
    
    @Transactional
    public Produto atualizar(Long id, Produto dadosNovos) {
        // 1. Busca o produto original no banco
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        // 2. Atualiza os campos (certifique-se de que os nomes batem com sua entidade)
        produtoExistente.setNomeProduto(dadosNovos.getNomeProduto());
        produtoExistente.setPrecoProduto(dadosNovos.getPrecoProduto());
        produtoExistente.setQuantidadeEstoque(dadosNovos.getQuantidadeEstoque());
        produtoExistente.setDescricao(dadosNovos.getDescricao());

        // 3. Salva o objeto já existente (o JPA fará o UPDATE automaticamente)
        return produtoRepository.save(produtoExistente);
    }

    @Transactional
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }
}