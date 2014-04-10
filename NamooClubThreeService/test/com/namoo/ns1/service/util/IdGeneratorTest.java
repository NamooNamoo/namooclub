package com.namoo.ns1.service.util;

import org.junit.Test;

public class IdGeneratorTest {

	@Test
	public void test() {

		String id = IdGenerator.getNextNo(IdGeneratorTest.class);
		System.out.println(id);
	}

}
