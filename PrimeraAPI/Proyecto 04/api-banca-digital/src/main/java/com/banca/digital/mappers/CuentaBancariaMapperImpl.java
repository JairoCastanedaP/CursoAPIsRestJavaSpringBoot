package com.banca.digital.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.dto.CuentaActualDTO;
import com.banca.digital.dto.CuentaAhorroDTO;
import com.banca.digital.entidades.Cliente;
import com.banca.digital.entidades.CuentaActual;
import com.banca.digital.entidades.CuentaAhorro;

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
	
	public CuentaAhorroDTO mapperDeCuentaAhorro(CuentaAhorro cuentaAhorro) {
		CuentaAhorroDTO cuentaAhorroDTO= new CuentaAhorroDTO();
		BeanUtils.copyProperties(cuentaAhorro, cuentaAhorroDTO);
		cuentaAhorroDTO.setClienteDTO(mappeerDeCliente(cuentaAhorro.getCliente()));
		cuentaAhorroDTO.setTipo(cuentaAhorro.getClass().getSimpleName());
		return cuentaAhorroDTO;
	}
	//De un DTO a una entidad, y se retorna
	public CuentaAhorro mapperDeCuentaAhorroDTO(CuentaAhorroDTO cuentaAhorroDTO) {
		CuentaAhorro cuentaAhorro = new CuentaAhorro();
		BeanUtils.copyProperties(cuentaAhorroDTO, cuentaAhorro);
		cuentaAhorro.setCliente(mappeerDeClienteDTO(cuentaAhorroDTO.getClienteDTO()));
		return cuentaAhorro;
	}
	
	// de una entidad a DTO
	public CuentaActualDTO mapperDeCuentaActual(CuentaActual cuentaActual) {
		CuentaActualDTO cuentaActualDTO= new CuentaActualDTO();
		BeanUtils.copyProperties(cuentaActual, cuentaActualDTO);
		cuentaActualDTO.setClienteDTO(mappeerDeCliente(cuentaActual.getCliente()));
		cuentaActualDTO.setTipo(cuentaActual.getClass().getSimpleName());
		return cuentaActualDTO;
	}
	//De un DTO a una entidad, y se retorna
	public CuentaActual mapperDeCuentaActualDTO(CuentaActualDTO cuentaActualDTO) {
		CuentaActual cuentaActual = new CuentaActual();
		BeanUtils.copyProperties(cuentaActualDTO, cuentaActual);
		cuentaActual.setCliente(mappeerDeClienteDTO(cuentaActualDTO.getClienteDTO()));
		return cuentaActual;
	}
	
}
