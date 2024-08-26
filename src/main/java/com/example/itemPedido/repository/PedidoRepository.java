package com.example.itemPedido.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.itemPedido.entity.ItemPedido;
import com.example.itemPedido.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Pedido findByItens(List<ItemPedido> itens);
	
	Page<Pedido> findByItensId(Long id, Pageable pageable);
	
}
