package com.banca.digital.excepciones;

public class BalanceInsuficienteException extends Exception{

	public BalanceInsuficienteException(String message) {
		super(message);
	}
}
