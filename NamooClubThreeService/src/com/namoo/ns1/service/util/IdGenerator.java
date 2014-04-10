package com.namoo.ns1.service.util;

import com.namoo.ns1.data.EntityManager;

import dom.entity.IdValue;

public class IdGenerator {


	public static <T> String getNextNo(Class<T> clazz){
		//
		EntityManager em = EntityManager.getInstance();

		String idName = clazz.getName();
		IdValue idValue = em.find(IdValue.class, idName);

		if (idValue == null) {
			idValue = new IdValue(idName);
		}

		idValue.increaseValue();

		//
		em.store(idValue);

		return idValue.getValue() + "";
	}
}
