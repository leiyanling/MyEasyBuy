package com.lyl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyl.entity.User;
import com.lyl.service.user.UserService;
import com.lyl.service.user.UserServiceImpl;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();
		switch (action) {
		case "login":// 登录
			String password = request.getParameter("loginName");
			String loginName = request.getParameter("loginName");
			User loginUser = userService.login(loginName, password);
			if (loginUser != null) {
				request.getSession().setAttribute("loginUser", loginUser);
				out.print("{ \"message\":\"登录成功\",  \"status\":1 }");
			} else {
				out.print("{ \"message\":\"登录失败\",  \"status\":1 }");
			}
			break;
		case "register":// 注册
			User user = new User();
			user.setEmail(request.getParameter("email"));
			user.setIdentityCode(request.getParameter("identityCode"));
			user.setLoginName(request.getParameter("loginName"));
			user.setMobile(request.getParameter("mobile"));
			// 密码需要加密
			user.setPassword(request.getParameter("password"));
			user.setSex(Integer.parseInt(request.getParameter("sex")));
			user.setType(0);
			user.setUserName(request.getParameter("userName"));

			if (userService.add(user)) {// 注册成功
				out.print("{ \"message\":\"注册成功\",  \"status\":1 }");

			} else {
				out.print("{ \"message\":\"注册失败\",  \"status\":0 }");

			}

			break;

		}

		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
