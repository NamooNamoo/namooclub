package com.namoo.ns1.web.controller.manage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

import dom.entity.Club;

@WebServlet("/manage/club_modify.do")
public class ClubModifyController extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -8851384356740206964L;

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
		String description = req.getParameter("description");
		String name = req.getParameter("name");
		String clubId = req.getParameter("club_id");


		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();

		Club club = clubService.findClub(clubId);



		clubService.modifyCommunity(clubId, name, description);

		resp.sendRedirect("club.do");
	}
}
