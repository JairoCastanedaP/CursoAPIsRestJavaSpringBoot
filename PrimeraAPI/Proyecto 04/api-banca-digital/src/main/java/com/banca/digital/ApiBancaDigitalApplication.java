package com.banca.digital;



import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.banca.digital.entidades.Cliente;
import com.banca.digital.entidades.CuentaActual;
import com.banca.digital.entidades.CuentaAhorro;
import com.banca.digital.entidades.OperacionCuenta;
import com.banca.digital.enums.EstadoCuenta;
import com.banca.digital.enums.TipoOperacion;
import com.banca.digital.repositorios.ClienteRepository;
import com.banca.digital.repositorios.CuentaBancariaRespository;
import com.banca.digital.repositorios.OperacionCuentaRespository;
import com.banca.digital.servicios.BancoService;

@SpringBootApplication
public class ApiBancaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBancaDigitalApplication.class, args);	}
	
	@Bean
	CommandLineRunner commandLineRunner(BancoService bancoService) {
		return args ->{
			bancoService.consultar();
		};
	}
	//@Bean
	CommandLineRunner start(ClienteRepository clienteRepository, CuentaBancariaRespository cuentaBancariaRespository, OperacionCuentaRespository operacionCuentaRespository) {
		return args->{
			Stream.of("Christian","Julen", "pedro","Leo").forEach(nombre-> {
				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setEmail(nombre+"@gmail.com");
				clienteRepository.save(cliente);
			});
			//se asignan las cuentas bancarias
			clienteRepository.findAll().forEach(cliente ->{
				CuentaActual cuentaActual= new CuentaActual();
				cuentaActual.setId(UUID.randomUUID().toString());
				cuentaActual.setBalance(Math.random()*90000);
				cuentaActual.setFechaCreacion(new Date());
				cuentaActual.setEstadoCuenta(EstadoCuenta.CREADA);
				cuentaActual.setCliente(cliente);
				cuentaActual.setSobregiro(9000);
				cuentaBancariaRespository.save(cuentaActual);
				
				CuentaAhorro cuentaAhorro= new CuentaAhorro();
				cuentaAhorro.setId(UUID.randomUUID().toString());
				cuentaAhorro.setBalance(Math.random()*90000);
				cuentaAhorro.setFechaCreacion(new Date());
				cuentaAhorro.setEstadoCuenta(EstadoCuenta.CREADA);
				cuentaAhorro.setCliente(cliente);
				cuentaAhorro.setTasaInteres(5.5);
				cuentaBancariaRespository.save(cuentaAhorro);
			});
			
			//Se agregan las operaciones
			
			cuentaBancariaRespository.findAll().forEach(cuentaBancaria->{
				for(int i=0; i<10;i++) {
					
					OperacionCuenta operacionCuenta= new OperacionCuenta();
					operacionCuenta.setFechaOperacion(new Date());
					operacionCuenta.setMonto((Math.random()*12000));
					operacionCuenta.setTipoOperacion(Math.random()>05 ? TipoOperacion.DEBITO : TipoOperacion.CREDITO);
					operacionCuenta.setCuentaBancaria(cuentaBancaria);
					operacionCuentaRespository.save(operacionCuenta);
				}
			});
		};
		

		
	}

}
