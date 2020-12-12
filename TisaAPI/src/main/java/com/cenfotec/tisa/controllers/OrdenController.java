package com.cenfotec.tisa.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.tisa.model.Cliente;
import com.cenfotec.tisa.model.Orden;
import com.cenfotec.tisa.repository.ClienteRepository;
import com.cenfotec.tisa.repository.OrdenRepository;
import com.cenfotec.tisa.controllers.ClienteController;


@RestController
@RequestMapping({ "/ordenes" })
public class OrdenController {
	
	private OrdenRepository repository;
	
	OrdenController(OrdenRepository ordenRepository) {
		this.repository = ordenRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Orden> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Orden create(@RequestBody Orden orden) {
		
		// Me estoy trayendo los datos con el id del cliente pero no se pueded 
		// guardar sólo el ID. Debo buscar el cliente por ID y guardarlo 
		// en el campo de Cliente perteneciente a las ordenes
		
		/*
		 * Set<Orden> ordenes = orden.getCliente().; ordenes.add(orden); Cliente cliente
		 * = orden.getCliente(); cliente.setOrdenes(ordenes);
		 */

		/*
		 * Optional<Cliente> cliente =
		 * clienterepository.findById(orden.getCliente().getId()); Cliente cli =
		 * cliente.get(); cli.setOrdenes(orden);
		 */
		
		if(orden.getTipoProducto().toLowerCase().equals("tasa") || 
		   orden.getTipoProducto().toLowerCase().equals("camiseta") || 
		   orden.getTipoProducto().toLowerCase().equals("almohadon") ||
		   orden.getTipoProducto().toLowerCase().equals("vaso"))
		{
			return repository.save(orden);
		}else {
			orden.setTipoProducto("Tipo de producto no encontrado");
			return orden;
		}
		
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Orden> update(@PathVariable("id") long id, @RequestBody Orden orden) {
		return repository.findById(id).map(record -> {
			record.setTipoProducto(orden.getTipoProducto());
			record.setCantidad(orden.getCantidad());
			record.setPath(orden.getPath());
			record.setEstado(orden.getEstado());
			Orden updated = repository.save(record);
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
