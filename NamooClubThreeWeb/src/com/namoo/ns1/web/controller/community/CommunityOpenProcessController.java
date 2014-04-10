package com.namoo.ns1.web.controller.community;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.anootation.LoginRequired;

import dom.entity.SocialPerson;

@WebServlet("/community/open.do")
@LoginRequired
public class CommunityOpenProcessController extends HttpServlet{

	private static final long serialVersionUID = 3467492067893239149L;

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
		TownerService townerService = NamooClubServiceFactory.getInstance().getTownerService();

		HttpSession session = req.getSession();

		String loginId = (String) session.getAttribute("loginId");
		SocialPerson person = townerService.findTowner(loginId);

		String communityName = req.getParameter("community_name");
		String description = req.getParameter("community_desc");
		String email = person.getEmail();
		String[] categories = req.getParameterValues("categories");

		communityService.registCommunity(communityName, description, email, categories);

		RequestDispatcher dispatcher = req.getRequestDispatcher("../main.do");
		dispatcher.forward(req, resp);
	}
}
