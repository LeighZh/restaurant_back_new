package com.znsd.restaurant.servlet.System;

import com.alibaba.fastjson.JSON;
import com.znsd.restaurant.bean.system.AuthorityBean;
import com.znsd.restaurant.servers.System.TreeService;
import com.znsd.restaurant.servers.impl.System.TreeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("appliaction/JSON;charsex=UTF-8");
		TreeService treeService = new TreeServiceImpl();
		String name = (String) request.getSession().getAttribute("name");
		List<AuthorityBean> queryAuthority = treeService.queryAuthority(name);
		response.getWriter().print(JSON.toJSONString(queryAuthority));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
