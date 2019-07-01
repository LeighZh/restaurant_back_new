package com.znsd.restaurant.servlet.order;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.znsd.restaurant.bean.order.IndentBean;
import com.znsd.restaurant.servers.order.IndentServers;
import com.znsd.restaurant.servers.impl.order.IndentServersImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class IndentServlet
 */
public class IndentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("appliaction/JSON;charsex=UTF-8");
		String mark = request.getParameter("judge");
		IndentServers ind = new IndentServersImpl();
		if(mark.equals("getTheOrder")){
			this.getTheOrder(request,ind,response);
		}else if(mark.equals("delete")){
			String id = request.getParameter("indentId");
			String name = request.getParameter("userName");
			String money = request.getParameter("money");
 			ind.delete(id,name,money);
		}else if(mark.equals("cancel")){
			String id = request.getParameter("indentId");
			ind.cancel(id);
		}else if(mark.equals("b")){
			String page = request.getParameter("page");
			String pageSize = request.getParameter("rows");
			String name = request.getParameter("userName");
			List<IndentBean> list = ind.main2(name,page,pageSize);
			String count = (String) ind.count2();
			response.getWriter().write("{\"total\":"+count+",\"rows\":"+ JSON.toJSONString(list)+"}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void getTheOrder(HttpServletRequest request,IndentServers ind, HttpServletResponse response){
		String orderId = (request.getParameter("orderId") == null || request.getParameter("orderId").equals("")) ? "0" : request.getParameter("orderId");
		System.out.println(orderId);
		System.out.println(orderId.getClass());
		System.out.println("子订单查询");
		List<IndentBean> res = ind.getTheOrder(Integer.parseInt(orderId));
		Gson gson = new GsonBuilder().create();
		String result = gson.toJson(res);
		System.out.println("result " + result);

		try {
			response.getWriter().write(result);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
