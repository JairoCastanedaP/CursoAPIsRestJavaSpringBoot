package com.banca.digital.servicios.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.dto.CuentaActualDTO;
import com.banca.digital.dto.CuentaAhorroDTO;
import com.banca.digital.dto.CuentaBancariaDTO;
import com.banca.digital.dto.HistorialCuentaDTO;
import com.banca.digital.dto.OperacionCuentaDTO;
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
import com.banca.digital.repositorios.OperacionCuentaRepository;
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
	private OperacionCuentaRepository operacionCuentaRespository;
	
	@Autowired
	private CuentaBancariaMapperImpl cuenBancariaMapperImpl;
	
	@Override
	public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
		
		logger.debug("Guardando un nuevo cliente");
		Cliente cliente = cuenBancariaMapperImpl.mappeerDeClienteDTO(clienteDTO);
		Cliente clienteBBDD= clienteRepository.save(cliente);
		return cuenBancariaMapperImpl.mappeerDeCliente(clienteBBDD);
	}

	@Override
	public CuentaActualDTO saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId)
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
		return cuenBancariaMapperImpl.mapperDeCuentaActual(cuentaActualBBDD);
	}

	@Override
	public CuentaAhorroDTO saveCuentaBancariaAhorro(double balanceInicial, double tasaInteres, Long clienteId)
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
		return cuenBancariaMapperImpl.mapperDeCuentaAhorro(cuentaAhorroBBDD);

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
	public CuentaBancariaDTO getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException {

		CuentaBancaria cuentaBancaria= cuentaBancariaRespository.findById(cuentaId)
				.orElseThrow(()-> new CuentaBancariaNotFoundException("Cuenta bancaria no encontrada"));
		if(cuentaBancaria instanceof CuentaAhorro) {
			CuentaAhorro cuentaAhorro= (CuentaAhorro)cuentaBancaria;
			return cuenBancariaMapperImpl.mapperDeCuentaAhorro(cuentaAhorro);
		}
		else {
			CuentaActual cuentaActual= (CuentaActual)cuentaBancaria;
			return cuenBancariaMapperImpl.mapperDeCuentaActual(cuentaActual);
		}
	}

	@Override
	public void debit(String cuentaId, double monto, String descripcion)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		
		CuentaBancaria cuentaBancaria= cuentaBancariaRespository.findById(cuentaId)
				.orElseThrow(()-> new CuentaBancariaNotFoundException("Cuenta bancaria no encontrada"));
		
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
	public List<CuentaBancariaDTO> listCuentasBancarias() {
		
		List<CuentaBancaria> cuentasBancarias = cuentaBancariaRespository.findAll();
		List<CuentaBancariaDTO> cuentasBancariasDTOs = cuentasBancarias.stream().map(cuentaBancaria -> {
			
			if(cuentaBancaria instanceof CuentaAhorro) {
				CuentaAhorro cuentaAhorro= (CuentaAhorro)cuentaBancaria;
				return cuenBancariaMapperImpl.mapperDeCuentaAhorro(cuentaAhorro);
			}
			else {
				CuentaActual cuentaActual= (CuentaActual)cuentaBancaria;
				return cuenBancariaMapperImpl.mapperDeCuentaActual(cuentaActual);
			}
		}).collect(Collectors.toList());
		return cuentasBancariasDTOs;
	}

	@Override
	public void credit(String cuentaId, double monto, String descripcion)
			throws CuentaBancariaNotFoundException, BalanceInsuficienteException {
		
		CuentaBancaria cuentaBancaria= cuentaBancariaRespository.findById(cuentaId)
				.orElseThrow(()-> new CuentaBancariaNotFoundException("Cuenta bancaria no encontrada"));
		
		
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

	@Override
	public ClienteDTO updateCliente(ClienteDTO clienteDTO) {
		logger.debug("Actualizando cliente");
		Cliente cliente = cuenBancariaMapperImpl.mappeerDeClienteDTO(clienteDTO);
		Cliente clienteBBDD = clienteRepository.save(cliente);
		return cuenBancariaMapperImpl.mappeerDeCliente(clienteBBDD);
	}

	@Override
	public void deleteCliente(Long clienteId) {
		clienteRepository.deleteById(clienteId);
		
	}

	 @Override
	    public List<OperacionCuentaDTO> listHistorialDeLaCuenta(String cuentaId) {
	        List<OperacionCuenta> operacionesDeCuenta = operacionCuentaRespository.findByCuentaBancariaId(cuentaId);
	        return operacionesDeCuenta.stream().map(operacionCuenta ->
	        cuenBancariaMapperImpl.mapperDeOperacionCuenta(operacionCuenta)
	        ).collect(Collectors.toList());
	    }

	 @Override
	    public HistorialCuentaDTO getHistorialCuenta(String cuentaId, int page, int size) throws CuentaBancariaNotFoundException {
	        CuentaBancaria cuentaBancaria = cuentaBancariaRespository.findById(cuentaId).orElse(null);

	        if(cuentaBancaria == null){
	            throw new CuentaBancariaNotFoundException("Cuenta no encontrada");
	        }

	        Page<OperacionCuenta> operacionesCuenta = operacionCuentaRespository.findByCuentaBancariaIdOrderByFechaOperacionDesc(cuentaId, PageRequest.of(page, size));
	        HistorialCuentaDTO historialCuentaDTO = new HistorialCuentaDTO();
	        List<OperacionCuentaDTO> operacionesCuentaDTOS = operacionesCuenta.getContent().stream().map(operacionCuenta -> cuenBancariaMapperImpl.mapperDeOperacionCuenta(operacionCuenta)).collect(Collectors.toList());
	        historialCuentaDTO.setOperacionesCuentaDTOS(operacionesCuentaDTOS);
	        historialCuentaDTO.setCuentaId(cuentaBancaria.getId());
	        historialCuentaDTO.setBalance(cuentaBancaria.getBalance());
	        historialCuentaDTO.setCurrentPage(page);
	        historialCuentaDTO.setPageSize(size);
	        historialCuentaDTO.setTotalPages(operacionesCuenta.getTotalPages());
	        return historialCuentaDTO;
	    }

	@Override
	public List<ClienteDTO> searchClientes(String keyword) {
		List<Cliente> clientes = clienteRepository.searchClientes(keyword);
		List<ClienteDTO> clientesDTOS = (List<ClienteDTO>) clientes.stream().map(cliente -> cuenBancariaMapperImpl.mappeerDeCliente(cliente));
		return clientesDTOS;
	}

}
