package com.banca.digital.dto;

import java.util.List;

import lombok.Data;

@Data
public class HistorialCuentaDTO {
	private String cuentaId;
	private double balance;
	private int currentPage;
	private int totalPages;
	private int pageSize;
	private List<OperacionCuentaDTO> operacionesCuentaDTOS;
	public HistorialCuentaDTO() {
		super();
	}
	public String getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(String cuentaId) {
		this.cuentaId = cuentaId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<OperacionCuentaDTO> getOperacionesCuentaDTOS() {
		return operacionesCuentaDTOS;
	}
	public void setOperacionesCuentaDTOS(List<OperacionCuentaDTO> operacionesCuentaDTOS) {
		this.operacionesCuentaDTOS = operacionesCuentaDTOS;
	}
	
	
	
}
