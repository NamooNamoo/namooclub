package com.namoo.ns1.web.controller.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

@WebServlet("/community/withdrawal.do")
public class CommunityWithdrawalProcessController extends HttpServlet{

	private static final long serialVersionUID = 8844210976891463139L;

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

		String communityName = req.getParameter("community_name");
		String email = (String) session.getAttribute("loginId");
		
		communityService.withdrawalCommunity(communityName, email);
		
		resp.sendRedirect("../main.do");
	}
}
