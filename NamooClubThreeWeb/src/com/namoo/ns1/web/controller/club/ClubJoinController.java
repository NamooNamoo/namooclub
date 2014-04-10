package com.namoo.ns1.web.controller.club;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.web.controller.anootation.DefaultController;
import com.namoo.ns1.web.controller.anootation.LoginRequired;

@WebServlet("/club/join.do")
@LoginRequired
public class ClubJoinController extends DefaultController {
	
	private static final long serialVersionUID = -4821770365482700212L;

/*	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		String club_id = req.getParameter("club_id");
		String community_id = req.getParameter("community_id");

		req.setAttribute("club_id", club_id);
		req.setAttribute("community_id", community_id);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/join.jsp");
		dispatcher.forward(req, resp);

	}*/

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		String club_id = req.getParameter("club_id");
		String community_id = req.getParameter("community_id");

		req.setAttribute("club_id", club_id);
		req.setAttribute("community_id", community_id);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/club/join.jsp");
		dispatcher.forward(req, resp);
	}

}
