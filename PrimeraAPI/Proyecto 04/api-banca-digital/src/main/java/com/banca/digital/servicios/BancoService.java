package com.banca.digital.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banca.digital.entidades.CuentaActual;
import com.banca.digital.entidades.CuentaAhorro;
import com.banca.digital.entidades.CuentaBancaria;
import com.banca.digital.repositorios.CuentaBancariaRespository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class BancoService {
	
	@Autowired
	private CuentaBancariaRespository cuentaBancariaRespository;
	
	public void consultar() {
		CuentaBancaria cuentaBancariaBBDD = cuentaBancariaRespository.findById("065c5d05-7fba-4cb7-b501-7ab01554fee2").orElse(null);
		
		if(cuentaBancariaBBDD != null) {
			System.out.println("*******************************************");
			System.out.println("ID : "+ cuentaBancariaBBDD.getId());
			System.out.println("Balance de lacuenta : "+ cuentaBancariaBBDD.getBalance());
			System.out.println("Estado de la cuenta : "+ cuentaBancariaBBDD.getEstadoCuenta());
			System.out.println("Fecha de creación  : "+ cuentaBancariaBBDD.getFechaCreacion());
			System.out.println("Cliente : "+ cuentaBancariaBBDD.getCliente().getNombre());
			System.out.println("Nombre de la clase : "+ cuentaBancariaBBDD.getClass().getSimpleName());
		
			if(cuentaBancariaBBDD instanceof CuentaActual) {
				System.out.println("Sobregiro : "+ ((CuentaActual) cuentaBancariaBBDD).getSobregiro());
			}
			else if(cuentaBancariaBBDD instanceof CuentaAhorro){
				System.out.println("Tasa de interes : "+((CuentaAhorro) cuentaBancariaBBDD).getTasaInteres() );
			}
			
			cuentaBancariaBBDD.getOperacionCuenta().forEach(operacionCuenta ->{
				System.out.println("------------------------------------------");
				System.out.println("Tipo de operación : "+ operacionCuenta.getTipoOperacion());
				System.out.println("Fecha de operación : "+ operacionCuenta.getFechaOperacion());
				System.out.println("Monto: "+ operacionCuenta.getMonto());
			});;
		}
	}
}