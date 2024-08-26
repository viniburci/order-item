package com.example.itemPedido.controller;


import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.itemPedido.entity.Produto;
import com.example.itemPedido.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<Page<Produto>> buscarTodosProdutos(@RequestParam(name = "nome", required = false, defaultValue = "") String nome, Pageable pageable) {
		
		return ResponseEntity.ok(produtoService.buscarPorNome(nome, pageable));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable(name = "id") Long id){
		
		var produto = produtoService.buscarPorId(id);
		
		if(produto != null) {
			return ResponseEntity.ok(produtoService.buscarPorId(id));
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) throws URISyntaxException {
		
		Produto produtoBancoDeDados = produtoService.salvarProduto(produto);
		
		if(produtoBancoDeDados != null) {
			return ResponseEntity.created(new URI("/produto/" + produtoBancoDeDados.getId())).body(produtoBancoDeDados);
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	
}
