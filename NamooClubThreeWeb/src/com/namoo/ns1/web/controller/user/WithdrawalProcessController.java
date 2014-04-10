package com.namoo.ns1.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

@WebServlet("/user/withdrawal.do")
public class WithdrawalProcessController extends HttpServlet{

	private static final long serialVersionUID = -3898530063479048578L;

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
		TownerService townerService = NamooClubServiceFactory.getInstance().getTownerService();
		HttpSession session = req.getSession();

		String email = (String) session.getAttribute("loginId");
		
		townerService.removeTowner(email);
		
		session.removeAttribute("loginId");
		
		resp.sendRedirect("../main.do");
	}
}
