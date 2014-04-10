package com.namoo.ns1.service.facade;

import java.util.List;
import java.util.Map;

import com.namoo.ns1.service.logic.exception.NamooRuntimeException;

import dom.entity.Club;
import dom.entity.ClubMember;
import dom.entity.SocialPerson;

public interface ClubService {

	/**
	 *
	 * @param clubName
	 * @param adminName
	 * @param email
	 * @param password
	 */

	/**
	 * [주민으로 등록되지 않은 경우] 클럽 개설
	 *
	 * 주민 가입을 처리하고 나서 클럽를 개설한다.
	 * 이미 존재하는 주민인 경우 예외가 발생한다.
	 * @param clubName
	 * @param description
	 * @param adminName
	 * @param email
	 * @param password
	 */



	//public void registClub(String communityName, String clubName, String description, String adminName, String email, String password);

	/**
	 * [주민으로 등록된 경우] 클럽 개설
	 *
	 * 이미 주민으로 가입되어 있는 경우 이메일만 필요하다.
	 * 존재하지 않는 주민인 경우 예외가 발생한다.
	 *
	 * @param clubName
	 * @param description
	 * @param email
	 *
	 * @throws NamooRuntimeException
	 */
	public void registClub(String communityName, String clubName, String description, String email, String category);

	/**
	 *
	 * @param clubName
	 */
	public Club findClub(String communityId, String id);




	/**
	 * [주민으로 등록되지 않은 경우] 클럽 가입
	 *
	 * 주민 가입을 처리하고 나서 클럽에 가입한다.
	 * 이미 존재하는 주민인 경우 예외가 발생한다.
	 *
	 * @param clubName
	 * @param name
	 * @param email
	 * @param password
	 *
	 * @throws NamooRuntimeException
	 */
	public void joinAsMember(String communityName, String clubName, String name, String email, String password);

	/**
	 * [주민으로 등록된 경우] 클럽 가입
	 *
	 * 이미 주민으로 가입되어 있는 경우 이메일만 필요하다.
	 * 존재하지 않는 주민인 경우 예외가 발생한다.
	 *
	 * @param clubName
	 * @param email
	 *
	 * @throws NamooRuntimeException
	 */
	public void joinAsMember(String communityName, String clubName, String email);

	/**
	 * @return
	 */


	/**
	 * 이메일로 클럽 회원 찾기
	 *
	 * @param clubName
	 * @param email
	 * @return
	 */
	public ClubMember findClubMember(String communityName, String clubName, String email);


	/**
	 * 클럽 회원목록 조회
	 *
	 * @param clubName
	 * @return
	 */
	public List<ClubMember> findAllClubMember(String communityName, String clubName);

	/**
	 *
	 * @param clubName
	 */
	public int countMembers(String communityName, String clubName);

	/**
	 * @param clubName
	 */
	public void removeClub(String communityName, String clubName);

	/**
	 * 자신이 회원으로 있는 클럽 목록조회
	 *
	 * @param email
	 * @return
	 */
	public List<Club> findBelongClubs(String communityName, String email);

	/**
	 * 자신이 관리하는 클럽 목록조회
	 *
	 * @param email
	 * @return
	 */
	public List<Club> findManagedClubs(String communityName, String email);

	/**
	 * 클럽에서 탈퇴하기
	 *
	 * @param clubName
	 * @param email
	 */
	public void withdrawalClub(String communityName, String clubName, String email);

	List<Club> findAllClubs(String communityId);

	SocialPerson findPerson(String email);

	public void withdrawal(String community_id, String club_id, String email);

	public Club findClub(String club_id);

	public void modifyCommunity(String clubId, String name, String description);

}
