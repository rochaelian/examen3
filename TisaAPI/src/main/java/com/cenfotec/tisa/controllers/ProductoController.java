package com.cenfotec.tisa.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.tisa.model.Producto;
import com.cenfotec.tisa.repository.ProductoRepository;

@RestController
@RequestMapping({ "/productos" })
public class ProductoController {

	private ProductoRepository repository;
	
	ProductoController(ProductoRepository productoRepository) {
		this.repository = productoRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Producto> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Producto create(@RequestBody Producto producto) {
		return repository.save(producto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Producto> update(@PathVariable("id") long id, @RequestBody Producto producto) {
		return repository.findById(id).map(record -> {
			record.setTipoProducto(producto.getTipoProducto());
			record.setCosto(producto.getCosto());
			Producto updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
