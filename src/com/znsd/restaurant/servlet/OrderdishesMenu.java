package com.znsd.restaurant.servlet;

import com.znsd.restaurant.servers.OrderdishesMenuService;
import com.znsd.restaurant.servers.impl.OrderdishesMenuImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderdishesMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setCharacterEncoding("utf-8");
		  	request.setCharacterEncoding("utf-8");
		  	String select=request.getParameter("select");
		  OrderdishesMenuService oms=new OrderdishesMenuImpl();
		  	
		  	//用户选择的所有菜 
		  	if(select.equals("getSelectMenu")){
		  		String menuId=request.getParameter("id");
		  		String user=(String) request.getSession().getAttribute("userName"); 
		  		oms.getSelectMenu(Integer.parseInt(menuId),user);  
		  	}else if(select.equals("ping")){
		  		 
		  	 String ping=request.getParameter("ping"); 
		  	 String menuName=request.getParameter("menuName");
		  	String user=(String) request.getSession().getAttribute("userName");
		  	String text=request.getParameter("text");
		  	 oms.evaluate(user,menuName,ping,text);
		  	 
		  		
		  	}
		  	
		  	
	}

}
