package com.example.itemPedido.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.itemPedido.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Page<Produto> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);
	
	Optional<Produto> findById(Long id);
	
	Page<Produto> findAll(Pageable pageable);
}
