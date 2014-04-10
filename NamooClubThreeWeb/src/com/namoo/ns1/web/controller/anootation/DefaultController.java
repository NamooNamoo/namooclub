package com.namoo.ns1.web.controller.anootation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class DefaultController extends HttpServlet {
	//
	private static final long serialVersionUID = 5419831456544653632L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		
		if(loginId == null) { 
			if(this.getClass().getAnnotation(LoginRequired.class)!=null){
				resp.sendRedirect("/NamooClub/view/common/login_message.xhtml");
				return;
			}
		}

		process(req, resp);

	}

	protected abstract void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
		
}
