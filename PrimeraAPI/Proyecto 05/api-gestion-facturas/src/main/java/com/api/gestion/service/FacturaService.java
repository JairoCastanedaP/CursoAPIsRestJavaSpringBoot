package com.api.gestion.service;

import com.api.gestion.pojo.Factura;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface FacturaService{
	

    ResponseEntity<String> generateReport(Map<String,Object> requestMap);

    ResponseEntity<List<Factura>> getFacturas();

    ResponseEntity<byte[]> getPdf(Map<String,Object> requestMap);

    ResponseEntity<String> deleteFactura(Integer id);
}
