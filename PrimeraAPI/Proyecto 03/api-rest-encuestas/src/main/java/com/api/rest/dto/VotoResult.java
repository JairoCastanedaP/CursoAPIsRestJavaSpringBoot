package com.api.rest.dto;

import java.util.Collection;

import lombok.Data;

@Data
public class VotoResult {

	private int totalVotos;
	private Collection<OpcionCount> results;
	public VotoResult(int totalVotos, Collection<OpcionCount> results) {
		super();
		this.totalVotos = totalVotos;
		this.results = results;
	}
	
	public VotoResult() {
		super();
	}

	public int getTotalVotos() {
		return totalVotos;
	}
	public void setTotalVotos(int totalVotos) {
		this.totalVotos = totalVotos;
	}
	public Collection<OpcionCount> getResults() {
		return results;
	}
	public void setResults(Collection<OpcionCount> results) {
		this.results = results;
	}
	
	
	
}
