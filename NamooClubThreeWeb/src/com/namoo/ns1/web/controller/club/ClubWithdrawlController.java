package com.namoo.ns1.web.controller.club;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

@WebServlet("/club/withdrawal.do")
public class ClubWithdrawlController extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -3523076684739677509L;

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
		String email = (String) req.getSession().getAttribute("loginId");

		String community_id = req.getParameter("community_id");
		String club_id = req.getParameter("club_id");

		clubService.withdrawal(community_id, club_id, email);


		resp.sendRedirect("../community/main.do?community_name="+community_id);
	}

}
