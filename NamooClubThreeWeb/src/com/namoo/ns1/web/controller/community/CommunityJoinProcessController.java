package com.namoo.ns1.web.controller.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.CommunityService;
import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;
import com.namoo.ns1.web.controller.anootation.LoginRequired;

@WebServlet("/community/join.do")
@LoginRequired
public class CommunityJoinProcessController extends HttpServlet {

	private static final long serialVersionUID = 5537704466401117989L;

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
		//String queryString = (String) req.getAttribute("queryString");

		//System.out.println("쿼리 : "+queryString);

		String communityName = req.getParameter("community_name");//아직 안받아왔음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		CommunityService communityService = NamooClubServiceFactory.getInstance().getCommunityService();

		String loginId = (String) req.getSession().getAttribute("loginId");

		communityService.joinAsMember(communityName, loginId);
		resp.sendRedirect("../main.do");
	}
}
