package com.example.itemPedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.itemPedido.entity.Produto;
import com.example.itemPedido.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Page<Produto> buscarPorNome(String nome, Pageable pageable) {
		return produtoRepository.findByNomeIgnoreCaseContaining(nome, pageable);
	}
	
	public Page<Produto> buscarTodos(Pageable pageable){
		return produtoRepository.findAll(pageable);
	}
	
	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
}
