package com.banca.digital.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.entidades.Cliente;

@Service
public class CuentaBancariaMapperImpl {

	public ClienteDTO mappeerDeCliente(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		BeanUtils.copyProperties(cliente, clienteDTO);
		return clienteDTO;
	}
	
	public Cliente mappeerDeClienteDTO(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		BeanUtils.copyProperties(clienteDTO, cliente);
		return cliente;
	}
}
