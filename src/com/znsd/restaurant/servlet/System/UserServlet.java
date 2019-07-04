package com.znsd.restaurant.servlet.System;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.znsd.restaurant.MD5Encode;
import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.bean.system.UserBean;
import com.znsd.restaurant.servers.System.UserService;
import com.znsd.restaurant.servers.impl.System.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("appliaction/JSON;charsex=UTF-8");
		String judge = request.getParameter("judge");
		System.out.println(judge);
		UserService userServlet = new UserServiceImpl();
		if(judge.equals("saveOrUpdateUser")){
			this.saveOrUpdateUser(request, response, userServlet);
		}else if(judge.equals("delete")){
			this.delete(request, response, userServlet);
		}else if(judge.equals("afterRegister")){
			this.afterRegister(request, response, userServlet);
		}else if(judge.equals("getUsers")){
			this.getUsers(request, response, userServlet);
		}else if(judge.equals("getSession")){
			this.getSession(request, response, userServlet);
		}else if(judge.equals("loginOut")){
			this.loginOut(request, response, userServlet);
		}else if(judge.equals("resetPassword")){
			this.resetPassword(request,response,userServlet);
		}else if(judge.equals("resetUserPassword")){
			this.resetUserPassword(request,response,userServlet);
		}else if(judge.equals("getAdmins")){
			this.getAdmins(request,response,userServlet);
		}else if(judge.equals("resetAdminPassword")){
			this.resetAdminPassword(request,response,userServlet);
		}else if(judge.equals("saveOrUpdateAdmin")){
			this.saveOrUpdateAdmin(request,response,userServlet);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void saveOrUpdateUser(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		boolean res = false;
		String id = (request.getParameter("id") == null || request.getParameter("id").equals("")) ? "0" : request.getParameter("id");
		System.out.println(id);
		String loginName = request.getParameter("loginName") ;
		String trueName = request.getParameter("trueName") ;
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		//若用户id为空，为保存
		if (Integer.parseInt(id) == 0){
			System.out.println("更新用户");
			//若当前登录名已经存在，则抛出用户已存在的异常
			System.out.println(userServlet.getUserByLoginName(loginName).size());
			if(userServlet.getUserByLoginName(loginName).size()== 0){
				System.out.println("更新用户");
				res = userServlet.insert(new UserBean(loginName,trueName,phone,email,address));
		}
		}else{
			//不是新用户，进行用户更新
			System.out.println("修改用户");
			res = userServlet.updata(new UserBean(Integer.parseInt(id),loginName,trueName,phone,email,address));
		}


		try {
			System.out.println("执行结果：" + String.valueOf(res));
			response.getWriter().print(JSON.toJSONString(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		String id = request.getParameter("id");
		boolean res  = userServlet.delete(Integer.valueOf(id));
		if(res == true){
			request.getSession().removeAttribute("name");
			request.getSession().removeAttribute("id");
		}
		try {
			response.getWriter().print(JSON.toJSONString(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void afterRegister(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		password = MD5Encode.MD5Encode(password,"utf8");
		password = MD5Encode.MD5Encode(password,"utf8");
		UserBean afterRegister = userServlet.afterRegister(name, password);
		boolean res = false;
		if(afterRegister != null){
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("id", afterRegister.getUserId());
			res = true;
		}
		try {
			response.getWriter().print(JSON.toJSONString(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getUsers(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		String id = request.getParameter("id") == null ? "0" : request.getParameter("id");
		String loginName = request.getParameter("loginName") == null ? null : request.getParameter("loginName").equals("") ? null : request.getParameter("loginName");
		String trueName = request.getParameter("trueName") == null ? null : request.getParameter("trueName").equals("") ? null : request.getParameter("trueName");
		String phone = request.getParameter("phone") == null ? null : request.getParameter("phone").equals("") ? null : request.getParameter("phone");
		String email = request.getParameter("email") == null ? null : request.getParameter("email").equals("") ? null : request.getParameter("email");
		String address = request.getParameter("address") == null ? null : request.getParameter("address").equals("") ? null : request.getParameter("address");
		String createTime = (request.getParameter("createTime") == null || request.getParameter("createTime").equals("null"))? null : request.getParameter("createTime").equals("") ? null : request.getParameter("createTime");
		String money = (request.getParameter("money") == null || request.getParameter("money").equals("null") || request.getParameter("money").equals("")) ? "-1" : request.getParameter("money");
		System.out.println("result ");
		System.out.println(loginName);
		System.out.println(createTime);
		System.out.println(money);
		List<UserBean> query = userServlet.afterQuery(new UserBean(Integer.parseInt(id),loginName,createTime,trueName,phone,email,address,Double.parseDouble(money)));

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

	public void getSession(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		HttpSession session = request.getSession();
		/*Enumeration<String> attrs = session.getAttributeNames();
		while (attrs.hasMoreElements()) {
			// 获取session键值
			String name = attrs.nextElement().toString();
			// 根据键值取session中的值
			Object vakue = session.getAttribute(name);
			// 打印结果
			System.out.println("------" + name + ":" + vakue + "--------\n");
		}*/

        String userName = (String)session.getAttribute("name");
        PrintWriter out;
        try {
            System.out.println(userName);
            out = response.getWriter();
            out.print(userName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	public void loginOut(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		HttpSession session = request.getSession();
		session.setAttribute("id",null);
		session.setAttribute("name",null);
		/*Enumeration<String> attrs = session.getAttributeNames();
		while (attrs.hasMoreElements()) {
			// 获取session键值
			String name = attrs.nextElement().toString();
			// 根据键值取session中的值
			Object vakue = session.getAttribute(name);
			// 打印结果
			System.out.println("------" + name + ":" + vakue + "--------\n");
		}*/

		String userName = (String)session.getAttribute("name");
		PrintWriter out;
		try {
			System.out.println(userName);
			out = response.getWriter();
			out.print(userName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetPassword(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		password = MD5Encode.MD5Encode(password,"utf8");
		password = MD5Encode.MD5Encode(password,"utf8");

		userServlet.resetPassword(name, password);
		PrintWriter out;

		try {
			System.out.println(true);
			out = response.getWriter();
			out.print(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetUserPassword(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		String name = request.getParameter("id");
		String password = request.getParameter("newPassword");
		password = MD5Encode.MD5Encode(password,"utf8");
		password = MD5Encode.MD5Encode(password,"utf8");
		userServlet.resetUserPassword(name, password);
		PrintWriter out;
		try {
			System.out.println(true);
			out = response.getWriter();
			out.print(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getAdmins(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		String id = (request.getParameter("id").equals("")|| request.getParameter("id") == null) ? "0" : request.getParameter("id");
		String name = (request.getParameter("name") == null ||  request.getParameter("name").equals("")) ? null  : request.getParameter("name");
		String role = (request.getParameter("role") == null || request.getParameter("role").equals("")) ? null : request.getParameter("role");
		System.out.println("result ");
		System.out.println(id);
		System.out.println(name);
		System.out.println(role);
		List<UserBean> query = userServlet.getAdmins(new UserBean(Integer.parseInt(id),name,role));
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

	public void resetAdminPassword(HttpServletRequest request, HttpServletResponse response,UserService userServlet){
		String name = request.getParameter("id");
		String password = request.getParameter("password");
		password = MD5Encode.MD5Encode(password,"utf8");
		password = MD5Encode.MD5Encode(password,"utf8");
		Boolean res = userServlet.resetAdminPassword(Integer.parseInt(name), password);
		PrintWriter out;

		try {
			System.out.println(res);
			out = response.getWriter();
			out.print(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean saveOrUpdateAdmin(HttpServletRequest request, HttpServletResponse response,UserService userServlet) {
		String id = request.getParameter("id").equals("") ? "0" : request.getParameter("id");
		String name = request.getParameter("name").equals("") ? null : request.getParameter("name");
		String role = request.getParameter("role").equals("") ? null : request.getParameter("role");
		System.out.println(request.getParameter("id").getClass());
		Boolean res = false;
		//若用户id为空，为保存
		if (Integer.parseInt(id) == 0) {
			System.out.println("更新用户");
			//若当前登录名已经存在，则抛出用户已存在的异常
			System.out.println(userServlet.getUserByLoginName(name).size());
			res = userServlet.insertAdmin(new UserBean(Integer.parseInt(id), name, role));
		} else {
			//不是新用户，进行用户更新
			System.out.println("修改用户");
			res = userServlet.updataAdmin(new UserBean(Integer.parseInt(id), name, role));
		}
		PrintWriter out;
		try {
			System.out.println(res);
			out = response.getWriter();
			out.print(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}




