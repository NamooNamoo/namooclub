package com.namoo.ns1.web.controller.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

@WebServlet("/club/remove.do")
public class ClubRemoveController extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 720645155254442304L;

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

		String community_id = req.getParameter("community_id");
		String club_id = req.getParameter("club_id");

		clubService.removeClub(community_id, club_id);

		resp.sendRedirect("../community/main.do?community_name="+community_id);
	}

}
