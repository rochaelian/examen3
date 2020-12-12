package com.cenfotec.clientesgraphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cenfotec.clientesgraphql.entities.Cliente;
import com.cenfotec.clientesgraphql.services.ClienteService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class ClienteMutation implements GraphQLMutationResolver{
	
	@Autowired
	private ClienteService clienteService;
	
	public Cliente createCliente(String nombre, String apellidos, String direccion, 
	String direccionCobro, String numTarjeta, String mesVencimiento, String anioVencimiento) {
	return this.clienteService.createCliente(nombre, apellidos, direccion, direccionCobro, numTarjeta, mesVencimiento, anioVencimiento);
	}
	
	public boolean deleteCliente(int id) {
		return this.clienteService.deleteCliente(id);
	}
	
	 public Cliente updateCliente(int id, String nombre, String apellidos, String direccion, 
				String direccionCobro, String numTarjeta, String mesVencimiento, String anioVencimiento) {
		 return this.clienteService.updateCliente(id, nombre, apellidos, direccion, direccionCobro, numTarjeta, mesVencimiento, anioVencimiento);
	 }
}
