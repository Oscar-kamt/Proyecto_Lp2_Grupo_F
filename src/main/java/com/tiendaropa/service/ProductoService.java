package com.tiendaropa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaropa.model.Producto;
import com.tiendaropa.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository repo;

	public List<Producto> listar() {

		return repo.findAll();

	}

	public Producto guardar(Producto p) {

		return repo.save(p);

	}

	public Producto buscar(Integer id) {

		return repo.findById(id).orElse(null);

	}

	public void eliminar(Integer id) {

		repo.deleteById(id);

	}

	public List<Producto> buscar(String nombre) {

		return repo.findByNombreContainingIgnoreCase(nombre);

	}

}
