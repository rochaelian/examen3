package com.cenfotec.clientesgraphql.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.clientesgraphql.entities.Cliente;
import com.cenfotec.clientesgraphql.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepo;
	
	public List<Cliente> getAllClientes(int count) {
	return this.clienteRepo.findAll().stream().limit(count).collect(Collectors.toList());
	}
	
	public Optional<Cliente> getCliente(int id) {
	return this.clienteRepo.findById(id);
	}
	
	public Cliente createCliente(String nombre, String apellidos, String direccion, String direccionCobro, String numTarjeta, String mesVencimiento, String anioVencimiento) {
	
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setDireccion(direccion);
		cliente.setDireccionCobro(direccionCobro);
		cliente.setNumTarjeta(numTarjeta);
		cliente.setMesVencimiento(mesVencimiento);
		cliente.setAnioVencimiento(anioVencimiento);
		
		return this.clienteRepo.save(cliente);
	}
	
	public boolean deleteCliente(int id) {
		this.clienteRepo.deleteById(id);
		return true;
	}
	
	public Cliente updateCliente(int id, String nombre, String apellidos, String direccion, String direccionCobro, String numTarjeta, String mesVencimiento, String anioVencimiento){
	    Optional<Cliente> opCliente = clienteRepo.findById(id);

	    Cliente cliente = opCliente.get();
	    
	    if (opCliente.isPresent()) {
	    	
	      if (nombre != null)
	    	  cliente.setNombre(nombre);
	      if (apellidos != null)
	    	  cliente.setApellidos(apellidos);
	      if (direccion != null)
	    	  cliente.setDireccion(direccion);
	      if (direccionCobro != null)
	    	  cliente.setDireccionCobro(direccionCobro);
	      if (numTarjeta != null)
	    	  cliente.setNumTarjeta(numTarjeta);
	      if (mesVencimiento != null) 
	    	  cliente.setMesVencimiento(mesVencimiento);
	      if (anioVencimiento != null)
	    	  cliente.setAnioVencimiento(anioVencimiento);

	      clienteRepo.save(cliente);
	      
	    }
	    return cliente;
	    
	    
	}

}
