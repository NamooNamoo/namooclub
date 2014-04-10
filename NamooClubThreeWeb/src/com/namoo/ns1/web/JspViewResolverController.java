package com.namoo.ns1.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/view/*")
public class JspViewResolverController  extends HttpServlet  {


	private static final long serialVersionUID = -5680783133750842140L;

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//
		String prefix = req.getContextPath() + req.getServletPath();//contextPath : /NamooClub,    servletPath : "/view/*"  -> /NamooClub/view
		String fullUrl = req.getRequestURI();// requestURI : 사용자가 요청한 전체주소

		System.out.println( "fullUrl : " +fullUrl);

		/*StringBuilder queryStr = new StringBuilder();
		for (Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
			//
			if (entry.getValue() != null) {
				String key = entry.getKey();
				for (String value : entry.getValue()) {
					if (queryStr.length() > 0) {
						queryStr.append("&");
					}
					queryStr.append(key);
					queryStr.append("=");
					queryStr.append(URLEncoder.encode(value, req.getCharacterEncoding()));
				}
			}
		}
		req.setAttribute("queryString", queryStr);*/

		if(fullUrl.equals("/NamooClub/view/community/join.xhtml") || 
				fullUrl.equals("/NamooClub/view/community/open.xhtml")){
			HttpSession session = req.getSession();
			String loginId = (String) session.getAttribute("loginId");
			
			if(loginId == null) {
				//
				resp.sendRedirect("/NamooClub/view/common/login_message.xhtml");
				return;
				
			} else {
				//
				String realPath = fullUrl.replace(prefix, "/WEB-INF/views").replace(".xhtml", ".jsp");
				RequestDispatcher dispatcher = req.getRequestDispatcher(realPath);
				dispatcher.forward(req, resp);
			}
			
		} else {
			
			String realPath = fullUrl.replace(prefix, "/WEB-INF/views").replace(".xhtml", ".jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher(realPath);
			dispatcher.forward(req, resp);
			
		}
	}
}
