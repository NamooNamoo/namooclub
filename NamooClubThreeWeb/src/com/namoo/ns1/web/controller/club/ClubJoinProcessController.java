package com.namoo.ns1.web.controller.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.anootation.LoginRequired;

@WebServlet("/club/join_pro.do")
@LoginRequired
public class ClubJoinProcessController extends HttpServlet {

	private static final long serialVersionUID = -814865035755908164L;

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
		String clubId = req.getParameter("club_id");

		ClubService clubService = NamooClubServiceFactory.getInstance().getClubService();

		String loginId = (String) req.getSession().getAttribute("loginId");
		String communityId = req.getParameter("community_id");
		//System.out.println(clubName);

		//clubService.findClub(communityId, clubId).;

		clubService.joinAsMember(communityId, clubId, loginId);
		resp.sendRedirect("../community/main.do?community_name="+communityId);
	}
}
