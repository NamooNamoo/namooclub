package com.namoo.ns1.web.controller.club;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

import dom.entity.Club;

@WebServlet("/club/main.do")
public class ClubMainController extends HttpServlet {

	private static final long serialVersionUID = -4246358558327339075L;

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
		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();
		String communityId = req.getParameter("community_id");
		String id = req.getParameter("club_id");

		Club club = clubService.findClub(communityId, id);

		req.setAttribute("club", club);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/main.jsp");
		dispatcher.forward(req, resp);
	}
}
