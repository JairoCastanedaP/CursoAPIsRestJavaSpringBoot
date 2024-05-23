package com.banca.digital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.entidades.Cliente;
import com.banca.digital.servicios.CuentaBancariaService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	
	@Autowired
	private CuentaBancariaService cuentaBancariaService;

	@GetMapping("/clientes")
	public List<ClienteDTO> listarClientes(){
		return cuentaBancariaService.listClientes();
	}
}
