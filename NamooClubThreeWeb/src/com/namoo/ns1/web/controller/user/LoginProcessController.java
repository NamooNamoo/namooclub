package com.namoo.ns1.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.service.facade.TownerService;
import com.namoo.ns1.service.factory.NamooClubServiceFactory;

@WebServlet("/user/login.do")
public class LoginProcessController extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -3654608386183372407L;

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
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");

		TownerService townerService = NamooClubServiceFactory.getInstance().getTownerService();
		boolean login = townerService.loginAsTowner(userId, password);
		if(login){
			System.out.println("로긴성공");
			req.getSession().setAttribute("loginId", userId);
			resp.sendRedirect("../main.do");
		}
		else{
			System.out.println("로긴실패");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("../view/user/login.xhtml");
//			dispatcher.forward(req, resp);
			
			resp.sendRedirect("../view/user/login.xhtml");
		}
	}


}
