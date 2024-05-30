package com.banca.digital.servicios;

import java.util.List;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.dto.CuentaActualDTO;
import com.banca.digital.dto.CuentaAhorroDTO;
import com.banca.digital.dto.CuentaBancariaDTO;
import com.banca.digital.dto.OperacionCuentaDTO;
import com.banca.digital.entidades.Cliente;
import com.banca.digital.entidades.CuentaActual;
import com.banca.digital.entidades.CuentaAhorro;
import com.banca.digital.entidades.CuentaBancaria;
import com.banca.digital.excepciones.BalanceInsuficienteException;
import com.banca.digital.excepciones.ClienteNotFoundExcepcton;
import com.banca.digital.excepciones.CuentaBancariaNotFoundException;

public interface CuentaBancariaService {

	ClienteDTO saveCliente(ClienteDTO clienteDTO);
	
	ClienteDTO getCliente(Long clienteId) throws ClienteNotFoundExcepcton;
	
	ClienteDTO updateCliente(ClienteDTO clienteId);
	
	void deleteCliente(Long clienteId);
	
	
	CuentaActualDTO saveCuentaBancariaActual(double balanceInicial, double sobregiro, Long clienteId) throws ClienteNotFoundExcepcton;
	
	CuentaAhorroDTO saveCuentaBancariaAhorro(double balanceInicial, double tasaInteres, Long clienteId) throws ClienteNotFoundExcepcton;
	
	List<ClienteDTO> listClientes();
	
	CuentaBancariaDTO getCuentaBancaria(String cuentaId) throws CuentaBancariaNotFoundException;

	void debit(String cuentaId, double monto, String descripcion) throws CuentaBancariaNotFoundException, BalanceInsuficienteException;
	
	void credit(String cuentaId, double monto, String descripcion) throws CuentaBancariaNotFoundException, BalanceInsuficienteException;
	
	void transfer(String cuentaIdPropietario, String cuentaIdDestinatario, double monto) throws CuentaBancariaNotFoundException, BalanceInsuficienteException;
	
	List<CuentaBancariaDTO> listCuentasBancarias();
	
	List<OperacionCuentaDTO> listHistorialDeLaCuenta(String cuentaId);
	
}
