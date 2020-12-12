package com.cenfotec.clientesgraphql.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cenfotec.clientesgraphql.entities.Cliente;
import com.cenfotec.clientesgraphql.services.ClienteService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class ClienteQuery implements GraphQLQueryResolver {

	@Autowired
	private ClienteService clienteService;
	
	public List<Cliente> getClientes(int count) {
	return this.clienteService.getAllClientes(count);
	}
	
	public Optional<Cliente> getCliente(int id) {
	return this.clienteService.getCliente(id);
	}
}
