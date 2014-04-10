package com.namoo.ns1.service.facade;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.namoo.ns1.service.logic.ClubServiceLogic;
import com.namoo.ns1.service.logic.CommunityServiceLogic;
import com.namoo.ns1.service.logic.TownerServiceLogic;

import dom.entity.Club;

public class ClubServiceTest {

	private CommunityService communityService;
	private TownerService townerService;
	private ClubService clubService;

	private String communityId = "1";
	private String communityName = "Java 커뮤니티";
	private String adminName = "김현오";
	private String adminEmail = "hyunohkim@nextree.co.kr";



	@Before
	public void setUp() throws Exception {
		communityService = new CommunityServiceLogic();
		townerService = new TownerServiceLogic();
		clubService = new ClubServiceLogic();
	}

	/*@After
	public void tearDown() throws Exception {
		//
		if (communityService.findCommunity(communityName) != null) {
			communityService.removeCommunity(communityName);
		}

		if (townerService.findTowner(adminEmail) != null) {
			townerService.removeTowner(adminEmail);
		}
	}*/

	@Test
	public void testRegistClub() {

		//int beforeCount = clubService.countMembers(communityName);
		createCommunity();
		//int afterCount = communityService.countMembers(communityName);
		//assertEquals(beforeCount + 1, afterCount);
	}

	private void createCommunity() {
		//
		String password = "asdf1234";

		//communityService.registCommunity(communityName , "설명", adminName, adminEmail, password);
	}

	private void createClub(){
		//
		String password = "asdf1234";

		//clubService.registClub(communityName, "a", "a", adminName, adminEmail, password);
		}


}
