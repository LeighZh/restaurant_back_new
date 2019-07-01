package com.znsd.restaurant.servlet.meal;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.znsd.restaurant.bean.meal.MenuBean;
import com.znsd.restaurant.servers.impl.meal.MenuServersImpl;
import com.znsd.restaurant.servers.meal.MenuServers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("appliaction/JSON;charsex=UTF-8");
		/*MenuServers menu=new MenuServersImpl();
		List<MenuBean> list=menu.query();
		String josn=JSON.toJSONString(list);
		response.getWriter().println(josn);*/
		MenuServers menu = new MenuServersImpl();
		String judge = request.getParameter("judge");
		System.out.println(judge);
		if(judge.equals("query")){
			this.getMenus(request,response,menu);
		}
	}
	public void menuAdd(HttpServletRequest request, HttpServletResponse response,MenuServers menu) throws ServletException, IOException{
		String menuname = request.getParameter("menuname");
		String price = request.getParameter("price");
		String cook = request.getParameter("cook");
		String menutype = request.getParameter("menutype");
		String describe = request.getParameter("describe");
		//String name=menu.add(new MenuBean(menuname,Double.parseDouble(price),cook,menutype,describe));
//		if(name==null||name.equals("")){
//			System.out.println(name+"***");
//			request.getRequestDispatcher("afterManage/menu.jsp").forward(request,response);
//		}
//	}
//	public void menuQuery(HttpServletRequest request, HttpServletResponse response,MenuServers menu){
//		String parameter = request.getParameter("page");
//		String rows = request.getParameter("rows");
//		int start = (Integer.parseInt(parameter)-1)*Integer.parseInt(rows);
//		List<MenuBean> query = menu.query();
//		try {
//			int count = menu.getCount();
//			response.getWriter().print("{\"total\":"+count+",\"rows\":"+JSON.toJSONString(query)+"}");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	public void getMenus(HttpServletRequest request, HttpServletResponse response,MenuServers menuServlet){
		String mealId = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String mealName = request.getParameter("name") == null ? null : request.getParameter("name").equals("") ? null : request.getParameter("name");
		String seriesName = request.getParameter("series") == null ? null : request.getParameter("series").equals("") ? null : request.getParameter("series");
		System.out.println("menuservlet-------------------------------------------------");

		List<MenuBean> query =menuServlet.query(new MenuBean(Integer.parseInt(mealId),mealName,Integer.parseInt(seriesName)));

		System.out.println(query.toString());
		Gson gson = new GsonBuilder().create();
		String result = gson.toJson(query);
		System.out.println("result " + result);

		try {
			response.getWriter().write(result);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
