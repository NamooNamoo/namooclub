package com.namoo.ns1.web.controller.club;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.ns1.service.facade.ClubService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

@WebServlet("/club/open_pro.do")
public class ClubOpenProcessController extends HttpServlet{

	private static final long serialVersionUID = -748773963521606791L;

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

		HttpSession session = req.getSession();

		String loginId = (String) session.getAttribute("loginId");
		
		if(loginId==null){
			resp.sendRedirect("/NamooClub/view/user/login.xhtml");
		}

		String communityId = req.getParameter("community_id");
		String clubId = req.getParameter("club_name");
		String description = req.getParameter("club_desc");
		String category = req.getParameter("category");

		System.out.println("클럽 카테고리 : "+category);

		clubService.registClub(communityId, clubId, description, loginId, category);

		RequestDispatcher dispatcher = req.getRequestDispatcher("../community/main.do?community_name="+communityId);
		dispatcher.forward(req, resp);
	}
}
