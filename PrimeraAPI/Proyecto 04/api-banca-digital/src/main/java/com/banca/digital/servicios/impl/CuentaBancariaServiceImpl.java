package com.banca.digital.servicios.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.entidades.Cliente;
import com.banca.digital.entidades.CuentaActual;
import com.banca.digital.entidades.CuentaAhorro;
import com.banca.digital.entidades.CuentaBancaria;
import com.banca.digital.entidades.OperacionCuenta;
import com.banca.digital.enums.TipoOperacion;
import com.banca.digital.excepciones.BalanceInsuficienteException;
import com.banca.digital.excepciones.ClienteNotFoundExcepcton;
import com.banca.digital.excepciones.CuentaBancariaNotFoundException;
import com.banca.digital.mappers.CuentaBancariaMapperImpl;
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
	
	@Autowired
	private CuentaBancariaMapperImpl cuenBancariaMapperImpl;
	
	@Override
	public Cliente saveCliente(ClienteDTO clienteDTO) {
		
		logger.debug("Guardando un nuevo cliente");
		Cliente cliente = cuenBancariaMapperImpl.mappeerDeClienteDTO(clienteDTO);
		Cliente clienteBBDD= clienteRepository.save(cliente);
		return cuenBancariaMapperImpl.mappeerDeCliente(clienteBBDD);
	}

	@Override
	public CuentaActual saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId)
			throws ClienteNotFoundExcepcton {
		
		Cliente cliente= clienteRepository.findById(clienteId).orElse(null);
		
		if(cliente == null) {
			throw new ClienteNotFoundExcepcton("Cliente no encontrado");
		}
		
		CuentaActual cuentaActual = new CuentaActual();
		cuentaActual.setId(UUID.randomUUID().toString());
		cuentaActual.setFechaCreacion(new Date());
		cuentaActual.setBalance(balanceInicial);
		cuentaActual.setSobregiro(sobregiro);
		cuentaActual.setCliente(cliente);
		
		CuentaActual cuentaActualBBDD = cuentaBancariaRespository.save(cuentaActual);
		return cuentaActualBBDD;
	}

	@Override
	public CuentaAhorro saveCuentaBancariaAhorro(double balanceInicial, double tasaInteres, Long clienteId)
			throws ClienteNotFoundExcepcton {
		Cliente cliente= clienteRepository.findById(clienteId).orElse(null);
		
		if(cliente == null) {
			throw new ClienteNotFoundExcepcton("Cliente no encontrado");
		}
		
		CuentaAhorro cuentaAhorro = new CuentaAhorro();
		cuentaAhorro.setId(UUID.randomUUID().toString());
		cuentaAhorro.setFechaCreacion(new Date());
		cuentaAhorro.setBalance(balanceInicial);
		cuentaAhorro.setTasaInteres(tasaInteres);
		cuentaAhorro.setCliente(cliente);
		
		CuentaAhorro cuentaAhorroBBDD = cuentaBancariaRespository.save(cuentaAhorro);
		return cuentaAhorroBBDD;

	}

	@Override
	public List<ClienteDTO> listClientes() {

		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO> clienteDTOS = clientes.stream()
				.map(cliente -> cuenBancariaMapperImpl.mappeerDeCliente(cliente))
				.collect(Collectors.toList());
		
		return clienteDTOS;
	}

	@Override
	public CuentaBancaria getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException {

		CuentaBancaria cuentaBancaria= cuentaBancariaRespository.findById(cuentaId)
				.orElseThrow(()-> new CuentaBancariaNotFoundException("Cuenta bancaria no encontrada"));
		return cuentaBancaria;
	}

	@Override
	public void debit(String cuentaId, double monto, String descripcion)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		CuentaBancaria cuentaBancaria= getCuentaBancaria(cuentaId);
		if(cuentaBancaria.getBalance()<monto) {
			throw new BalanceInsuficienteException("Balance insuficiente");
		}
		
		OperacionCuenta operacionCuenta = new OperacionCuenta();
		operacionCuenta.setTipoOperacion(TipoOperacion.DEBITO);
		operacionCuenta.setMonto(monto);
		operacionCuenta.setDescripcion(descripcion);
		operacionCuenta.setFechaOperacion(new Date());
		operacionCuenta.setCuentaBancaria(cuentaBancaria);
		operacionCuentaRespository.save(operacionCuenta);
		cuentaBancaria.setBalance(cuentaBancaria.getBalance() - monto);
	}

	@Override
	public void transfer(String cuentaIdPropietario, String cuentaIdDestinatario, double monto)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		debit(cuentaIdPropietario,monto,"transferencia a : "+cuentaIdDestinatario);
		credit(cuentaIdDestinatario, monto,"transferencia de : " +cuentaIdPropietario);
	}

	@Override
	public List<CuentaBancaria> listCuentasBancarias() {
		
		return cuentaBancariaRespository.findAll();
	}

	@Override
	public void credit(String cuentaId, double monto, String descripcion)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		
		CuentaBancaria cuentaBancaria= getCuentaBancaria(cuentaId);
		
		
		OperacionCuenta operacionCuenta = new OperacionCuenta();
		operacionCuenta.setTipoOperacion(TipoOperacion.DEBITO);
		operacionCuenta.setMonto(monto);
		operacionCuenta.setDescripcion(descripcion);
		operacionCuenta.setFechaOperacion(new Date());
		operacionCuenta.setCuentaBancaria(cuentaBancaria);
		operacionCuentaRespository.save(operacionCuenta);
		cuentaBancaria.setBalance(cuentaBancaria.getBalance() + monto);
		
	}

	@Override
	public ClienteDTO getCliente(Long clienteId) throws ClienteNotFoundExcepcton {
		Cliente cliente = clienteRepository.findById(clienteId)
		.orElseThrow(() -> new ClienteNotFoundExcepcton("Cliente no encontrado"));

		return cuenBancariaMapperImpl.mappeerDeCliente(cliente);
	}

}
