package com.znsd.restaurant.servlet.meal;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.znsd.restaurant.bean.meal.MenuBean;
import com.znsd.restaurant.bean.system.UserBean;
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
		MenuServers menu = new MenuServersImpl();
		String judge = request.getParameter("judge");
		System.out.println(judge);
		if(judge.equals("query")){
			this.getMenus(request,response,menu);
		}else if(judge.equals("delete")){
			this.delete(request,response,menu);
		}else if(judge.equals("saveOrUpdate")){
			this.saveOrUpdate(request,response,menu);
		}
	}
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response,MenuServers menu) throws ServletException, IOException {
		boolean res = false;
		String mealId = (request.getParameter("mealId") == null || request.getParameter("mealId").equals("")) ? "0" : request.getParameter("mealId");
		System.out.println(mealId);
		String mealName = request.getParameter("mealName") ;
		String seriesId = (request.getParameter("seriesId")  == null || request.getParameter("seriesId").equals("")) ? "0" : request.getParameter("seriesId");
		System.out.println(seriesId);
		String mealSummarize = request.getParameter("mealSummarize");
		String mealPrice = (request.getParameter("mealPrice") == null || request.getParameter("mealPrice").equals("")) ? "0" : request.getParameter("mealPrice");
		String mealDescription = request.getParameter("mealDescription");

		System.out.println(new MenuBean(Integer.parseInt(mealId),mealName,Double.parseDouble(mealPrice),Integer.parseInt(seriesId),mealDescription,mealSummarize).toString());
		//若用户id为空，为保存
		if (Integer.parseInt(mealId) == 0){
			System.out.println("更新用户");
			res = menu.insert(new MenuBean(Integer.parseInt(mealId),mealName,Double.parseDouble(mealPrice),seriesId,mealDescription,mealSummarize));
		}else{
			//不是新用户，进行用户更新
			System.out.println("修改用户");
			res = menu.update(new MenuBean(Integer.parseInt(mealId),mealName,Double.parseDouble(mealPrice),seriesId,mealDescription,mealSummarize));
		}
		try {
			System.out.println("执行结果：" + String.valueOf(res));
			response.getWriter().print(JSON.toJSONString(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getMenus(HttpServletRequest request, HttpServletResponse response,MenuServers menuServlet){
		String mealId = (request.getParameter("id") == null || request.getParameter("id").equals("") ) ? "0" : request.getParameter("id");
		String mealName = request.getParameter("name") == null ? null : request.getParameter("name").equals("") ? null : request.getParameter("name");
		String seriesName = (request.getParameter("series") == null ||request.getParameter("series").equals("")) ?  "0" : request.getParameter("series");
		System.out.println(mealId);
		System.out.println(mealName);
		System.out.println(seriesName);
		MenuBean a = (new MenuBean(Integer.parseInt(mealId),mealName,Integer.parseInt(seriesName)));
		System.out.println(a.toString());
		System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
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

	public void delete(HttpServletRequest request, HttpServletResponse response,MenuServers menuServlet){
		String id = request.getParameter("id");
		System.out.println(id);
		boolean res  = menuServlet.delete(Integer.valueOf(id));
		System.out.println(res);
		try {
			response.getWriter().print(JSON.toJSONString(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
