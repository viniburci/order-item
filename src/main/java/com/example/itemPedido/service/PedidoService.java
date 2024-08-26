package com.example.itemPedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.itemPedido.entity.ItemPedido;
import com.example.itemPedido.entity.Pedido;
import com.example.itemPedido.entity.Produto;
import com.example.itemPedido.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Page<Pedido> buscarTodosPedidos(Pageable pageable) {
		return pedidoRepository.findAll(pageable);
	}
	
	public Pedido buscarPorId(Long id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	public Pedido criarPedido(Pedido pedido) {
		
		if(pedido.getItens() == null) {
			//return responseentity badrequest
		}
		Double total = 0.0;
		List<ItemPedido> itens = pedido.getItens();
		for (ItemPedido item : itens) {
			Produto produto = produtoService.buscarPorId(item.getProduto().getId());
			if(produto != null) {
				item.setPrecoUnitario(produto.getPreco());
				item.setPrecoTotal(item.getPrecoUnitario() * item.getQuantidade());
				
				total += item.getPrecoTotal();
			}
			
		}
		pedido.setTotal(total);
		
		return pedidoRepository.save(pedido);
	}
	
	
}
