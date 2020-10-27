package com.minhacor.appdemo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhacor.appdemo.domain.Pedido;
import com.minhacor.appdemo.repositories.PedidoRepository;
import com.minhacor.appdemo.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository categoriaRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 
	}
}
