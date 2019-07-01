package com.znsd.restaurant.servlet;

import com.alibaba.fastjson.JSON;
import com.znsd.restaurant.bean.VegetableTypeBean;
import com.znsd.restaurant.servers.CookingServers;
import com.znsd.restaurant.servers.impl.CookingServersImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		CookingServers cs=new CookingServersImpl();
		String select=request.getParameter("select");
		String name=request.getParameter("name");
		System.out.println(select+"999");
		if(select.equals("add")){
			String man=cs.add(name);
			response.getWriter().println(man);
		}else if(select.equals("delete")){
			cs.delete(name);
		}else{
			List<VegetableTypeBean> list=cs.query();
			String josn= JSON.toJSONString(list);
			response.getWriter().println(josn);
		}
	}

}
