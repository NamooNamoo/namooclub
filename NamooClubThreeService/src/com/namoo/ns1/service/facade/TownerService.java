package com.namoo.ns1.service.facade;

import java.util.List;

import dom.entity.SocialPerson;

public interface TownerService {
	
	boolean loginAsTowner(String email, String password);
	void registTowner(String name, String email, String password);
	void removeTowner(String email);
	SocialPerson findTowner(String email);
	List<SocialPerson> findAllTowner();
	
}
