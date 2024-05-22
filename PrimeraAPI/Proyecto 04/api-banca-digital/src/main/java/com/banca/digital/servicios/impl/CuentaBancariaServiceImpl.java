package com.banca.digital.servicios.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banca.digital.entidades.Cliente;
import com.banca.digital.entidades.CuentaActual;
import com.banca.digital.entidades.CuentaAhorro;
import com.banca.digital.entidades.CuentaBancaria;
import com.banca.digital.excepciones.BalanceInsuficienteException;
import com.banca.digital.excepciones.ClienteNotFoundExcepcton;
import com.banca.digital.excepciones.CuentaBancariaNotFoundException;
import com.banca.digital.repositorios.ClienteRepository;
import com.banca.digital.repositorios.CuentaBancariaRespository;
import com.banca.digital.repositorios.OperacionCuentaRespository;
import com.banca.digital.servicios.CuentaBancariaService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.*;

@Service
@Transactional
@Slf4j

public class CuentaBancariaServiceImpl implements CuentaBancariaService{


	private static final Logger logger = LoggerFactory.getLogger(CuentaBancariaServiceImpl.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CuentaBancariaRespository cuentaBancariaRespository;
	
	@Autowired
	private OperacionCuentaRespository operacionCuentaRespository;
	
	@Override
	public Cliente saveCliente(Cliente cliente) {
		
		logger.debug("Guardando un nuevo cliente");
		Cliente clienteBBDD = clienteRepository.save(cliente);
		return clienteBBDD;
	}

	@Override
	public CuentaActual saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId)
			throws ClienteNotFoundExcepcton {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CuentaAhorro saveCuentaBancariaAhorro(double balanceInicial, double tasaInteres, Long clienteId)
			throws ClienteNotFoundExcepcton {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> listClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CuentaBancaria getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void debit(String cuentaId, double monto, String descripcion)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transfer(String cuentaIdPropietario, String cuentaIdDestinatario, double monto)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CuentaBancaria> listCuentasBancarias() {
		// TODO Auto-generated method stub
		return null;
	}

}
