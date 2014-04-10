package com.namoo.ns1.service.logic.exception;

public class NamooExceptionFactory {

	private NamooExceptionFactory() {
		//
	}
	
	public static NamooRuntimeException createRuntime(String msg) {
		//
		return new NamooRuntimeException(msg);
	}
}
