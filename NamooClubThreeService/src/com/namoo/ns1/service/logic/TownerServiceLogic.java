package com.namoo.ns1.service.logic;

import java.util.List;

import com.namoo.ns1.data.EntityManager;
import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.logic.exception.NamooRuntimeException;

import dom.entity.Community;
import dom.entity.SocialPerson;

public class TownerServiceLogic implements TownerService {

	private EntityManager em;



	public TownerServiceLogic() {
		this.em = EntityManager.getInstance();
	}

	@Override
	public boolean loginAsTowner(String email, String password) {
		//
		SocialPerson towner = em.find(SocialPerson.class, email);
		if (towner != null && towner.getPassword().equals(password)) {
			return true;
		}

		return false;
	}

	@Override
	public void registTowner(String name, String email, String password) {
		//
		if (em.find(SocialPerson.class, email) != null) {
			throw new RuntimeException("해당 주민이 이미 존재합니다. 등록할 수 없습니다.");
		}

		SocialPerson towner = new SocialPerson(name, email, password);
		em.store(towner);
	}

	@Override
	public void removeTowner(String email) {
		//
		// 커뮤니티의 회원으로 가입된 경우 삭제하지 못한다.
		List<Community> communities = em.findAll(Community.class);
		if (communities != null) {
			for (Community community : communities) {
				if (community.findMember(email) != null) {
					throw new NamooRuntimeException(
							"커뮤니티에 가입된 회원은 삭제할 수 없습니다. 먼저 커뮤니티를 탈퇴하세요. ["+community.getName()+"]");
				}
			}
		}

		em.remove(SocialPerson.class, email);
	}

	@Override
	public SocialPerson findTowner(String email) {
		//
		return em.find(SocialPerson.class, email);
	}

	@Override
	public List<SocialPerson> findAllTowner() {
		//
		return em.findAll(SocialPerson.class);
	}
}
