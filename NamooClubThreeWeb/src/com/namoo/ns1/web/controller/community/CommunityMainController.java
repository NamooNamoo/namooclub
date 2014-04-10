package com.namoo.ns1.web.controller.community;

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

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

import dom.entity.Club;
import dom.entity.Community;

@WebServlet("/community/main.do")
public class CommunityMainController extends HttpServlet {

	private static final long serialVersionUID = 8075594201606793335L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		CommunityService communityService = NamooClubServiceFactory
				.getInstance().getCommunityService();
		ClubService clubService = NamooClubServiceFactory.getInstance()
				.getClubService();

		HttpSession session = req.getSession();

		String email = (String) session.getAttribute("loginId");

		String communityName = req.getParameter("community_name");

		/*if(communityName.length()==0 ){
			communityName = (String) req.getAttribute("community_name");
		}*/

		List<Club> clubList = clubService.findAllClubs(communityName);


		List<Club> notMyclubList = clubService.findAllClubs(communityName);



		req.setAttribute("communityName", communityName);

		Community community = communityService.findCommunity(communityName);

		req.setAttribute("club_list", clubList);
		req.setAttribute("community", community);

		List<Club> myClubList = clubService.findBelongClubs(communityName, email);



		if (email == null) {
			req.setAttribute("community_list", clubList);
		} else {
			for (Iterator<Club> iter = clubList.iterator(); iter.hasNext();) {
				Club club = (Club) iter.next();
				for (Iterator<Club> iter2 = myClubList.iterator(); iter2.hasNext();) {

					Club myClub = (Club) iter2.next();
					if (club.getName().equals(myClub.getName())) {
						notMyclubList.remove(myClub);
					}
				}
			}
			req.setAttribute("my_community_list", myClubList);
			req.setAttribute("not_my_community_list", notMyclubList);
		}


		for(Club club : notMyclubList){
			System.out.println(club.getName());
		}


		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/community/main.jsp");
		dispatcher.forward(req, resp);
	}
}
