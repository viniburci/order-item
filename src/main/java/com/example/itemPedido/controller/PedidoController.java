package com.example.itemPedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.itemPedido.entity.Pedido;
import com.example.itemPedido.repository.PedidoRepository;
import com.example.itemPedido.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired PedidoService pedidoService;
	
	@Autowired PedidoRepository pedidoRepository;
	
	@GetMapping
	public ResponseEntity<Page<Pedido>> buscarTodosPedidos(Pageable pageable){
		return ResponseEntity.ok(pedidoService.buscarTodosPedidos(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(pedidoService.buscarPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
		return ResponseEntity.ok(pedidoService.criarPedido(pedido));
	}
	
	//test, delete later
	@GetMapping("/itens/{id}")
	public ResponseEntity<Page<Pedido>> buscarPorItensId(@PathVariable(name = "id") Long id, Pageable pageable) {
		return ResponseEntity.ok(pedidoRepository.findByItensId(id, pageable));
	}
	
}
