package com.znsd.restaurant.servlet.meal;

import com.alibaba.fastjson.JSON;
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
		System.out.println(judge+"66666666666");
		if(judge.equals("query")){
			MenuBean m;
			menu.query(m);
		}
//		if(judge==null || judge.equals("")){
//			List<MenuBean> list=menu.query(1,menu.getCount());
//			String josn=JSON.toJSONString(list);
//			response.getWriter().println(josn);
//		}
//		else if(judge.equals("afterQuery")){
//			this.menuQuery(request, response, menu);
//		}else if(judge.equals("add")){
//			System.out.println("90909090909090909");
//			this.menuAdd(request, response, menu);
//		}else if(judge.equals("delete")){
//			String name=request.getParameter("name");
//			menu.delete(name);
//		}else if(judge.equals("mod")){
//			String name=request.getParameter("usName");
//			String id=request.getParameter("id");
//			System.out.println(name);
//			System.out.println(id);
//			menu.mod(name,Integer.parseInt(id));
//		}

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
//	public void getMenus(HttpServletRequest request, HttpServletResponse response,MenuService menuServlet){
//		String mealId = request.getParameter("mealId") == null ? "0" : request.getParameter("mealId");
//		String mealName = request.getParameter(" mealName") == null ? null : request.getParameter(" mealName").equals("") ? null : request.getParameter(" mealName");
//		String seriesName = request.getParameter("seriesName") == null ? null : request.getParameter("seriesName").equals("") ? null : request.getParameter("seriesName");
//		String mealPrice = request.getParameter("mealPrice") == null ? null : request.getParameter("mealPrice").equals("") ? null : request.getParameter("mealPrice");
//		String mealImage = request.getParameter("mealImage") == null ? null : request.getParameter("mealImage").equals("") ? null : request.getParameter("mealImage");
//		String mealSummarize = request.getParameter("mealSummarize") == null ? null : request.getParameter("mealSummarize").equals("") ? null : request.getParameter("mealSummarize");
//		System.out.println("result ");
//		System.out.println(mealId);
//		System.out.println(mealName);
//		System.out.println(seriesName);
//		System.out.println(mealPrice);
//		System.out.println(mealImage);
//		System.out.println(mealSummarize);
//		List<MenuBean> query =menuServlet.query(new MenuBean(Integer.parseInt(id),loginName,createTime,trueName,phone,email,address,Double.parseDouble(money)));
//
//		Gson gson = new GsonBuilder().create();
//		String result = gson.toJson(query);
//		System.out.println("result " + result);
//
//		try {
//			response.getWriter().write(result);
//			response.getWriter().flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
