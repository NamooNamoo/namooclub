package com.namoo.ns1.common;

import java.io.Serializable;


public class StoredObject implements Serializable {
	//
	private static final long serialVersionUID = 5219918160509638602L;

	private String oId; 
	private String className; 
	private Object object; 
	
	//--------------------------------------------------------------------------
	public StoredObject(Identifiable entity) {
		// 
		this.className = entity.getClass().getName(); 
		this.oId = entity.getOId(); 
		this.object = entity; 
	}
	
	public static String createKey(String className, String oId) {
		// 
		return className + "." + oId; 
	}
	
	public static String createKey(Identifiable entity) {
		//
		String oId = entity.getOId(); 
		if (oId == null) {
			throw new RuntimeException("Doesn't have an ojbect id. --> " + entity.toString()); 
		}
		
		return createKey(entity.getClass().getName(), oId); 
	}

	public String getId() {
		// 
		return className + "." + oId; 
	}
	
	public String getOId() {
		return oId; 
	}
	
	public Object getObject() {
		return object; 
	}
}