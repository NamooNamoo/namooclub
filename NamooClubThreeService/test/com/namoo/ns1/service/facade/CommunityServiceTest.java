package com.namoo.ns1.service.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.ns1.service.logic.CommunityServiceLogic;
import com.namoo.ns1.service.logic.TownerServiceLogic;

import dom.entity.Community;

public class CommunityServiceTest {

	private CommunityService communityService;
	private TownerService townerService;

	private String communityName = "Java 커뮤니티";
	private String adminName = "김현오";
	private String adminEmail = "hyunohkim@nextree.co.kr";

	@Before
	public void setUp() {
		//
		communityService = new CommunityServiceLogic();
		townerService = new TownerServiceLogic();
	}

	@After
	public void tearDown() {
		//
		if (communityService.findCommunity(communityName) != null) {
			communityService.removeCommunity(communityName);
		}

		if (townerService.findTowner(adminEmail) != null) {
			townerService.removeTowner(adminEmail);
		}
	}

	@Test
	public void testRegistCommunity() {
		//
		int beforeCount = communityService.countMembers(communityName);
		createCommunity();
		int afterCount = communityService.countMembers(communityName);
		assertEquals(beforeCount + 1, afterCount);




	}

	@Test
	public void testFindCommunity() {
		//
		createCommunity();

		Community community = communityService.findCommunity(communityName);
		assertEquals(communityName, community.getName());
		assertEquals(adminName, community.getManager().getName());
	}

	@Test
	public void testJoinAsMember() {
		//
		createCommunity();

		int beforeCount = communityService.countMembers(communityName);

		String name = "홍길동";
		String email = "hong@gildong.net";
		String password = "aaaa1234";

		communityService.joinAsMember(communityName, name, email, password);

		int afterCount = communityService.countMembers(communityName);
		assertEquals(beforeCount + 1, afterCount);

		//
		townerService.removeTowner(email);
	}

	@Test
	public void testCountMembers() {
		//
		createCommunity();
		assertTrue(communityService.countMembers(communityName) > 0);
	}


	private void createCommunity() {
		//
		String password = "asdf1234";
		//communityService.registCommunity(communityName, adminName, adminEmail, password);
	}

}
