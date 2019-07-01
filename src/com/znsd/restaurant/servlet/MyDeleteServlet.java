package com.znsd.restaurant.servlet;

import com.znsd.restaurant.servers.impl.meal.MenuServersImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("appliaction/JSON;charsex=UTF-8");
		
		String judge = request.getParameter("judge");
		String data = request.getParameter("data");
		String usName = request.getParameter("usName");
		String id = request.getParameter("id");
	
		if("Menudelete".equals(judge)){
			this.Menudelete(request,response,data);
		}else if("Typealter".equals(judge)){
		
			//new MenuServersImpl().Typealter(usName,id);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void Menudelete(HttpServletRequest request, HttpServletResponse response,String data){
		if(data!=null && data!=""){
			new MenuServersImpl().delete(data);
		}
	}
}
