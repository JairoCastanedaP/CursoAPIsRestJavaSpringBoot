package com.api.gestion.rest;

import com.api.gestion.constantes.FacturaConstantes;
import com.api.gestion.pojo.Factura;
import com.api.gestion.service.FacturaService;
import com.api.gestion.util.FacturaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/factura")
public class FacturaController {
	

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/generarReporte")
    ResponseEntity<String> generarReporte(@RequestBody Map<String,Object> requestMap){
        try{
            return facturaService.generateReport(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getFacturas")
    public ResponseEntity<List<Factura>> listarFacturas(){
        try{
            return facturaService.getFacturas();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @PostMapping("/getPdf")
    public ResponseEntity<byte[]> obtenerPDF(@RequestBody Map<String,Object> requestMap){
        try{
            return facturaService.getPdf(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarFactura(@PathVariable Integer id){
        try{
            return facturaService.deleteFactura(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
