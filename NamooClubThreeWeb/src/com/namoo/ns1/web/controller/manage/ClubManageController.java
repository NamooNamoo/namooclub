package com.namoo.ns1.web.controller.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

import dom.entity.Club;
import dom.entity.Community;

@WebServlet("/manage/club.do")
public class ClubManageController extends HttpServlet{

	private static final long serialVersionUID = -7828472142558524181L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();

		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("loginId");

		List<Community> myCommunityList = communityService.findBelongCommunities(email);
		List<Club> clubList = new ArrayList<Club>();


		for(Community community : myCommunityList){
			Map<String, Club> allClub = community.getAllClub();


			Set<Entry<String, Club>> entrySet = allClub.entrySet();
			Iterator<Entry<String, Club>> iter = entrySet.iterator();

			while(iter.hasNext()){
				Club club = iter.next().getValue();
				if((club.getManager().getEmail()).equals(email)){
					clubList.add(club);
				}
			}

		}

		req.setAttribute("community_list", myCommunityList);
		req.setAttribute("club_list", clubList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/manage/club.jsp");
		dispatcher.forward(req, resp);
	}

}
