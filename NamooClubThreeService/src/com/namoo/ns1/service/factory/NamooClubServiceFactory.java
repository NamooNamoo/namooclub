package com.namoo.ns1.service.factory;
import com.namoo.ns1.service.logic.ClubServiceLogic;
import com.namoo.ns1.service.logic.CommunityServiceLogic;
import com.namoo.ns1.service.logic.TownerServiceLogic;
import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.facade.TownerService;

public class NamooClubServiceFactory {

	private static NamooClubServiceFactory instance = new NamooClubServiceFactory();

	private NamooClubServiceFactory(){
		//
	}

	public static NamooClubServiceFactory getInstance(){
		//
		return instance;
	}

	public CommunityService getCommunityService(){
		//
		return new CommunityServiceLogic();
	}

	public TownerService getTownerService() {
		// 
		return new TownerServiceLogic();
	}
	
	public ClubService getClubService() {
		// 
		return new ClubServiceLogic();
	}

}