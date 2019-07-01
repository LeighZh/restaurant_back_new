package com.znsd.restaurant.servlet.series;

import com.alibaba.fastjson.JSON;
import com.znsd.restaurant.bean.series.VegetableTypeBean;
import com.znsd.restaurant.servers.impl.series.CookingServersImpl;
import com.znsd.restaurant.servers.series.CookingServers;

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
		System.out.println(select);
		if(select.equals("add")){
			String man=cs.add(name);
			System.out.println(man);
			response.getWriter().println(man);
		}else if(select.equals("delete")){
			System.out.println(name);
			cs.delete(name);
			response.getWriter().println(true);
		}else if(select.equals("update")){
			String id = request.getParameter("id");
			boolean res = cs.update(Integer.parseInt(id),name);
			String josn= JSON.toJSONString(res);
			response.getWriter().println(josn);
		}else{
			List<VegetableTypeBean> list=cs.query();
			System.out.println(list.toString());
			String josn= JSON.toJSONString(list);
			response.getWriter().println(josn);
		}
	}

}
