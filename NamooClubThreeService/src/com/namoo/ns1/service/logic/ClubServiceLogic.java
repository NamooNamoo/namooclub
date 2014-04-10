package com.namoo.ns1.service.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.namoo.ns1.data.EntityManager;
import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.logic.exception.NamooExceptionFactory;
import com.namoo.ns1.service.util.IdGenerator;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.Community;
import dom.entity.SocialPerson;

public class ClubServiceLogic implements ClubService {

	private EntityManager em;

	public ClubServiceLogic() {
		this.em = EntityManager.getInstance();
	}

/*	@Override
	public void registClub(String communityId, String clubName,
			String description, String adminName, String email, String password) {
		//
		Community community = findCommunity(communityId);
		System.out.println("커뮤니티 이름"+community.getName());
		if (community.findClub(clubName) != null) {
			throw NamooExceptionFactory.createRuntime("이미 존재하는 클럽입니다.");
		}

		if (em.find(SocialPerson.class, email) != null) {
			throw NamooExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}
		SocialPerson admin = createPerson(adminName, email, password);
		String id = IdGenerator.getNextNo(Club.class);
		Club club = new Club(id, clubName, description, admin);

		community.addClub(club);
		em.store(club);
		em.store(community);
	}*/

	@Override
	public void registClub(String communityName, String clubName, String description, String email, String category) {
		//
		Community community = findCommunity(communityName);

		Date now = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		System.out.println(format.format(now)); // 20090529
		format = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);


		/*if (community.findClub(clubName) != null) {
			throw NamooExceptionFactory.createRuntime("이미 존재하는 클럽입니다.");
		}*/

		List<Club> clubs = findAllClubs(communityName);
		for(Club club : clubs){
			if(club.getCategory().equals(category)){
				throw NamooExceptionFactory.createRuntime("다른 카테고리를 선택해주세요.");
			}
		}


		SocialPerson towner = em.find(SocialPerson.class, email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}
		String id = IdGenerator.getNextNo(Club.class);
		Club club = new Club(id, clubName, description, towner, category, format.format(now));

		community.addClub(club);

		em.store(club);
		em.store(community);
	}

	private SocialPerson createPerson(String name, String email, String password) {
		//
		SocialPerson person = new SocialPerson(name, email, password);
		em.store(person);

		return person;
	}




	@Override
	public List<Club> findAllClubs(String communityId) {
		//
		if (findCommunity(communityId) == null)
			System.out.println("community가 null");
		Map<String, Club> clubs = findCommunity(communityId).getAllClub();


		List<Club> clubList = new ArrayList<Club>();

		if (clubs != null) {
			Set<Entry<String, Club>> entrySet = clubs.entrySet();
			Iterator<Entry<String, Club>> iter = entrySet.iterator();

			while (iter.hasNext()) {
				Club club = iter.next().getValue();
				clubList.add(club);
			}
			return clubList;
		}
		return null;
	}


	public Community findCommunity(String communityId) {
		//
		Community community = em.find(Community.class, communityId);

		if (community == null) {
			throw NamooExceptionFactory.createRuntime("커뮤니티가 존재하지 않습니다.");
		}
		return community;
	}

	@Override
	public Club findClub(String communityId, String clubId) {
		//
		Community community = findCommunity(communityId);
		Club club = community.findClub(clubId);
		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		return club;
	}

	// -----------------------------------------------------------------------------------완료

	@Override
	public void joinAsMember(String communityId, String clubId,
			String name, String email, String password) {
		//
		Club club = findClub(communityId, clubId);

		if (em.find(SocialPerson.class, email) != null) {
			throw NamooExceptionFactory.createRuntime("해당 주민이 이미 존재합니다.");
		}

		SocialPerson towner = createPerson(name, email, password);
		club.addMember(towner);

		em.store(club);
	}

	@Override
	public SocialPerson findPerson(String email){
		//
		return em.find(SocialPerson.class, email);
	}

	@Override
	public void joinAsMember(String communityId, String clubId, String email) {
		//
		Club club = findClub(communityId, clubId);

		SocialPerson towner = em.find(SocialPerson.class, email);
		if (towner == null) {
			throw NamooExceptionFactory.createRuntime("존재하지 않는 주민입니다.");
		}

		club.addMember(towner);
		em.store(club);
	}

	// --------------------------------------------------------------------------------------------------------------------------

	@Override
	public ClubMember findClubMember(String communityName, String clubName,
			String email) {
		//
		Club club = em.find(Club.class, clubName);

		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}

		for (ClubMember member : club.getMembers()) {
			if (member.getEmail().equals(email)) {
				return member;
			}
		}

		return null;
	}

	@Override
	public List<ClubMember> findAllClubMember(String communityName,
			String clubName) {
		//
		Club club = em.find(Club.class, clubName);

		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}
		return club.getMembers();
	}

	@Override
	public int countMembers(String communityName, String clubName) {
		//
		Club club = em.find(Club.class, clubName);
		if (club != null) {
			return club.getMembers().size();
		}

		return 0;
	}

	@Override
	public void removeClub(String communityId, String clubId) {
		//
		Community community = findCommunity(communityId);
		//community.removeClub(clubId);

		em.store(community);
		em.remove(findClub(communityId, clubId));

		community.removeClub(clubId);
	}

	@Override
	public List<Club> findBelongClubs(String communityId, String email) {
		//

		List<Club> clubs = findAllClubs(communityId);
		if (clubs == null)
			return null;

		List<Club> belongs = new ArrayList<>();
		for (Club club : clubs) {
			if (club.findMember(email) != null) {
				belongs.add(club);
			}
		}
		return belongs;
	}

	@Override
	public List<Club> findManagedClubs(String communityName, String email) {
		//
		List<Club> clubs = em.findAll(Club.class);
		if (clubs == null)
			return null;

		List<Club> manages = new ArrayList<>();
		for (Club club : clubs) {
			if (club.getManager().getEmail().equals(email)) {
				manages.add(club);
			}
		}
		return manages;
	}

	@Override
	public void withdrawalClub(String communityName, String clubName,
			String email) {
		//
		Club club = em.find(Club.class, clubName);
		if (club == null) {
			throw NamooExceptionFactory.createRuntime("클럽이 존재하지 않습니다.");
		}

		club.removeMember(email);
		em.store(club);
	}

	@Override
	public void withdrawal(String community_id, String club_id, String email) {
		//
		Community community = findCommunity(community_id);
		Club club = community.findClub(club_id);

		club.removeMember(email);
		em.store(community);
	}

	@Override
	public Club findClub(String club_id) {
		//
		return em.find(Club.class, club_id);
	}

	@Override
	public void modifyCommunity(String clubId, String name, String description) {
		//
		Club club = findClub(clubId);
		club.setDescription(description);
		club.setName(name);

		em.store(club);
	}
}
