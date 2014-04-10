package com.namoo.ns1.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

@WebServlet("/user/join.do")
public class JoinProcessController extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -6989751976165513911L;

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
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		TownerService townerService = NamooClubServiceFactory.getInstance().getTownerService();
		townerService.registTowner(name, email, password);

		resp.sendRedirect("../main.do");
	}


}
