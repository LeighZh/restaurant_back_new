package com.znsd.restaurant.servlet.order;

import com.alibaba.fastjson.JSON;
import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.servers.impl.order.RecordServersImpl;
import com.znsd.restaurant.servers.order.RecordServers;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class RecordServlet
 */
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RecordServers rs=new RecordServersImpl();
			String select=request.getParameter("judge");
			System.out.println("order:" +  select);
			if(select.equals("getOrders")){
				getOrders(request,rs,response);
			}else if(select.equals("deleteOrder")){
				deleteOrder(request,rs,response);
			}
		
	}

	public void getOrders(HttpServletRequest request,RecordServers rs,HttpServletResponse response) throws ServletException, IOException{
		String orderId = (request.getParameter("orderId") == null || request.getParameter("orderId").equals("")) ? "0" : request.getParameter("orderId");
		String userName = (request.getParameter("userName") == null || request.getParameter("userName").equals("")) ? null : request.getParameter("userName");
		String createTime = (request.getParameter("createTime") == null || request.getParameter("createTime").equals(""))? null :  request.getParameter("createTime");
//		System.out.println("---------------------------------------------------");
//		System.out.println(orderId);
//		System.out.println(userName);
//		System.out.println(createTime);
//		System.out.println(new RecordBean(Integer.parseInt(orderId),userName,createTime).toString());
//		System.out.println("---------------------------------------------------");

		List<RecordBean> res = rs.getOrders(new RecordBean(Integer.parseInt(orderId),userName,createTime));
		try {
			System.out.println("查询订单执行结果：" + String.valueOf(res));
			response.getWriter().print(JSON.toJSONString(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteOrder(HttpServletRequest request,RecordServers rs,HttpServletResponse response) throws ServletException, IOException{
		String orderId = (request.getParameter("orderId") == null || request.getParameter("orderId").equals("")) ? "0" : request.getParameter("orderId");

		System.out.println("---------------------------------------------------");
		System.out.println(orderId);
//		System.out.println(userName);
//		System.out.println(createTime);
//		System.out.println(new RecordBean(Integer.parseInt(orderId),userName,createTime).toString());
//		System.out.println("---------------------------------------------------");

		Boolean res = rs.deleteOrder(Integer.parseInt(orderId));
		try {
			System.out.println("删除订单执行结果：" + String.valueOf(res));
			response.getWriter().print(JSON.toJSONString(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
