package com.namoo.ns1.web.controller.manage;

import java.io.IOException;
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

@WebServlet("/manage/community.do")
public class CommunityManageController extends HttpServlet{

	private static final long serialVersionUID = 8207708627298996488L;

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

		List<Community> communityList = communityService.findManagedCommnities(email);

		req.setAttribute("community_list", communityList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/manage/community.jsp");
		dispatcher.forward(req, resp);
	}

}
