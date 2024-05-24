package com.banca.digital;



import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.banca.digital.dto.ClienteDTO;
import com.banca.digital.entidades.Cliente;
import com.banca.digital.entidades.CuentaActual;
import com.banca.digital.entidades.CuentaAhorro;
import com.banca.digital.entidades.CuentaBancaria;
import com.banca.digital.entidades.OperacionCuenta;
import com.banca.digital.enums.EstadoCuenta;
import com.banca.digital.enums.TipoOperacion;
import com.banca.digital.excepciones.ClienteNotFoundExcepcton;
import com.banca.digital.repositorios.ClienteRepository;
import com.banca.digital.repositorios.CuentaBancariaRespository;
import com.banca.digital.repositorios.OperacionCuentaRespository;
import com.banca.digital.servicios.BancoService;
import com.banca.digital.servicios.CuentaBancariaService;

@SpringBootApplication
public class ApiBancaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBancaDigitalApplication.class, args);	}
	
	//@Bean
	CommandLineRunner commandLineRunner(BancoService bancoService) {
		return args ->{
			bancoService.consultar();
		};
	}
	//@Bean
	CommandLineRunner start(CuentaBancariaService cuentaBancariaService) {
		return args->{
			Stream.of("Christian","Julen", "pedro","Leo").forEach(nombre-> {
				ClienteDTO cliente = new ClienteDTO();
				cliente.setNombre(nombre);
				cliente.setEmail(nombre+"@gmail.com");
				cuentaBancariaService.saveCliente(cliente);
				
				
			});	
			cuentaBancariaService.listClientes().forEach(cliente ->{
				try {
					
					cuentaBancariaService.saveCuentaBancariaActual(Math.random()*90000, 9000, cliente.getId());
					cuentaBancariaService.saveCuentaBancariaAhorro(120000, 5.5, cliente.getId());
					
					List<CuentaBancaria> cuentasBancarias= cuentaBancariaService.listCuentasBancarias();
					
					for(CuentaBancaria cuentaBancaria : cuentasBancarias) {
						for(int i=0;i<10;i++) {
							cuentaBancariaService.credit(cuentaBancaria.getId(), 10000+Math.random()*120000, "Credito");
							cuentaBancariaService.debit(cuentaBancaria.getId(), 1000+Math.random()*9000, "Debito");
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			});
		};			
	}

}
