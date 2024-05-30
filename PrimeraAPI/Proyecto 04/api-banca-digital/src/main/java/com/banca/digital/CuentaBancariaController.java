package com.banca.digital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banca.digital.dto.CuentaBancariaDTO;
import com.banca.digital.dto.HistorialCuentaDTO;
import com.banca.digital.dto.OperacionCuentaDTO;
import com.banca.digital.excepciones.CuentaBancariaNotFoundException;
import com.banca.digital.servicios.CuentaBancariaService;

@RestController
@RequestMapping("/api/v1")
public class CuentaBancariaController {

	@Autowired
	private CuentaBancariaService cuentaBancariaService;

	@GetMapping("/cuentas/{cuentaId}")
	public CuentaBancariaDTO listarDatosDeUnaCuentaBancaria(@PathVariable String cuentaId) throws CuentaBancariaNotFoundException {
		
		return cuentaBancariaService.getCuentaBancaria(cuentaId);
	}
	
	@GetMapping("/cuentas")
	public List<CuentaBancariaDTO> listarCuentasBancarias(){
		return cuentaBancariaService.listCuentasBancarias();
	}
	/*
	@GetMapping("/cuentas/{cuentaId}/operaciones")
	public List<OperacionCuentaDTO> listarHistorialDeCuentas(@PathVariable String cuentaId){
		return cuentaBancariaService.listHistorialDeLaCuenta(cuentaId);
	}
*/
	@GetMapping("/cuentas/{cuentaId}/pageOperaciones")
	public HistorialCuentaDTO listHistorialDeLaCuentaPaginado(@PathVariable String cuentaId, @RequestParam(name ="page", defaultValue = "0") int page, @RequestParam (name="size",defaultValue = "5")int size) throws CuentaBancariaNotFoundException{
		return cuentaBancariaService.getHistorialCuenta(cuentaId, page, size);
	}
	
}
