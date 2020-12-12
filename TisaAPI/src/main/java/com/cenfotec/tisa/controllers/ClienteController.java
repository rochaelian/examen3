package com.cenfotec.tisa.controllers;

import java.util.List;
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

@RestController
@RequestMapping({ "/clientes" })
public class ClienteController {

	private ClienteRepository repository;

	ClienteController(ClienteRepository clienteRepository) {
		this.repository = clienteRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Cliente> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Cliente create(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		return repository.findById(id).map(record -> {
			record.setNombre(cliente.getNombre());
			record.setApellidos(cliente.getApellidos());
			record.setDireccion(cliente.getDireccion());
			record.setDireccionCobro(cliente.getDireccionCobro());
			record.setNumTarjeta(cliente.getNumTarjeta());
			record.setMesVencimiento(cliente.getMesVencimiento());
			record.setAnioVencimiento(cliente.getAnioVencimiento());
			Cliente updated = repository.save(record);
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
	

	/*
	 * @DeleteMapping(path = { "/{id}" }) public ResponseEntity<?>
	 * delete(@PathVariable("id") long id) { return
	 * repository.findById(id).map(record -> {
	 * 
	 * List<Orden> lstOrdenes = ordenrepository.findAll(); record.getNombre();
	 * 
	 * for (int i = 0; i < lstOrdenes.size(); i++) {
	 * 
	 * Orden orden = lstOrdenes.get(i);
	 * 
	 * if (orden.getEstado().toLowerCase().equals("activa") &&
	 * orden.getCliente().equals(record)) { repository.deleteById(id); return
	 * ResponseEntity.ok().build(); } } return ResponseEntity.badRequest().build();
	 * 
	 * }).orElse(ResponseEntity.notFound().build()); }
	 */

	/*
	 * @RequestMapping(value = "/detalle/{id}") public String saveEdition(Model
	 * model, @PathVariable long id) { Optional<Workshop> possibleData =
	 * workshopService.get(id); if (possibleData.isPresent()) {
	 * model.addAttribute("workshopData", possibleData.get()); return "detalle"; }
	 * return "notfound"; }
	 */
}
