package com.namoo.ns1.service.facade;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.ns1.service.logic.TownerServiceLogic;

import dom.entity.SocialPerson;

public class TownerServiceTest {

	private TownerService townerService;
	private String name = "나주민";
	private String email = "jumin@gasan.net";
	private String password = "asdf1234";
	
	@Before
	public void setUp() {
		//
		townerService = new TownerServiceLogic();
	}
	
	@After
	public void tearDown() {
		//
		if (townerService.findTowner(email) != null) {
			townerService.removeTowner(email);
		}
	}
	
	
	@Test
	public void testLoginAsTowner() {
		//
		assertFalse(townerService.loginAsTowner("jumin@gasan.net", "asdf1234"));
		createTowner();
		assertTrue(townerService.loginAsTowner("jumin@gasan.net", "asdf1234"));
	}

	@Test
	public void testRegistTowner() {
		//
		createTowner();
		SocialPerson towner = townerService.findTowner(email);
		
		assertEquals(name, towner.getName());
		assertEquals(email, towner.getEmail());
		assertEquals(password, towner.getPassword());
	}

	@Test
	public void testRemoveTowner() {
		//
		createTowner();
		
		assertNotNull(townerService.findTowner(email));
		townerService.removeTowner(email);
		assertNull(townerService.findTowner(email));
		
	}

	private void createTowner() {
		//
		townerService.registTowner(name, email, password);
	}
}
