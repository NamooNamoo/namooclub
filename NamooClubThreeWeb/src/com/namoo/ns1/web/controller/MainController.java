package com.namoo.ns1.web.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

import dom.entity.Community;

@WebServlet("/main.do")
public class MainController extends HttpServlet{

	private static final long serialVersionUID = 1313734650176931560L;

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
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();

		HttpSession session = req.getSession();

		String email = (String) session.getAttribute("loginId");

		List<Community> communityList = communityService.findAllCommunities();

		List<Community> myCommunityList = communityService.findBelongCommunities(email);
		List<Community> notMyCommunityList = communityService.findAllCommunities();

		if (email == null) {
			req.setAttribute("community_list", communityList);
		} else {
			for (Iterator<Community> iter = communityList.iterator(); iter.hasNext();) {
				Community community = (Community) iter.next();
				for (Iterator<Community> iter2 = myCommunityList.iterator(); iter2.hasNext();) {

					Community myCommunity = (Community) iter2.next();
					if (community.getName().equals(myCommunity.getName())) {
						notMyCommunityList.remove(myCommunity);
					}
				}
			}
			req.setAttribute("my_community_list", myCommunityList);
			req.setAttribute("not_my_community_list", notMyCommunityList);
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
		dispatcher.forward(req, resp);
	}
}
