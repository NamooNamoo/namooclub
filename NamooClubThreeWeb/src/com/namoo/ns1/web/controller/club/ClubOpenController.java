package com.namoo.ns1.web.controller.club;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.anootation.DefaultController;
import com.namoo.ns1.web.controller.anootation.LoginRequired;

import dom.entity.Community;

@WebServlet("/club/open.do")
public class ClubOpenController extends DefaultController {

	private static final long serialVersionUID = -6816078929708750117L;

	/*@Override
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
		String communityId = req.getParameter("community_id");



		Community community = communityService.findCommunity(communityId);

		req.setAttribute("community", community);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/open.jsp");
		dispatcher.forward(req, resp);

	}*/

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();
		String communityId = req.getParameter("community_id");

		Community community = communityService.findCommunity(communityId);
		String[] categories = community.getCategories();

		req.setAttribute("community", community);
		req.setAttribute("categories", categories);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/open.jsp");
		dispatcher.forward(req, resp);
	}


}
