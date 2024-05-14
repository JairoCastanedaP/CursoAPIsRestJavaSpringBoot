package com.api.rest.dto;

import lombok.Data;

@Data
public class OpcionCount {
	
	private Long opcionId;
	private int count;
	public OpcionCount(Long opcionId, int count) {
		super();
		this.opcionId = opcionId;
		this.count = count;
	}
	
	
	public OpcionCount() {
		super();
	}


	public Long getOpcionId() {
		return opcionId;
	}
	public void setOpcionId(Long opcionId) {
		this.opcionId = opcionId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
