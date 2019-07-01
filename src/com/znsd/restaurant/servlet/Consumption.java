package com.znsd.restaurant.servlet;

import com.alibaba.fastjson.JSON;
import com.znsd.restaurant.bean.order.RecordBean;
import com.znsd.restaurant.servers.order.RecordServers;
import com.znsd.restaurant.servers.impl.order.RecordServersImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Consumption extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("appliaction/JSON;charsex=UTF-8");
		String judge = request.getParameter("judge");
		RecordServers recordServers = new RecordServersImpl();
		if(judge.equals("query")){
			this.consumptionQuery(request, response, recordServers);
		}else if(judge.equals("delete")){
			this.delete(request, response, recordServers);
		}else if(judge.equals("likeQuery")){
			this.likeQuery(request, response, recordServers);
		}else if(judge.equals("Deskdelete")){
			this.Deskdelete(request, response, recordServers);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void Deskdelete(HttpServletRequest request, HttpServletResponse response, RecordServers recordServers){
		String record = request.getParameter("data");
		if(record!=null && record!=""){
			recordServers.Deskdelete(record);
		}
	}

	public void consumptionQuery(HttpServletRequest request, HttpServletResponse response, RecordServers recordServers){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		int start = (Integer.parseInt(page)-1)*Integer.parseInt(rows);
		List<RecordBean> list = recordServers.consumptionQuery(start, Integer.parseInt(rows));
		int count = recordServers.getCount();
		try {
			response.getWriter().print("{\"total\":"+count+",\"rows\":"+ JSON.toJSONString(list)+"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response, RecordServers recordServers){
		String record = request.getParameter("data");
		if(record!=null && record!=""){
			recordServers.delete(record);
		}
	}
	
	public void likeQuery(HttpServletRequest request, HttpServletResponse response, RecordServers recordServers){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String likeName = request.getParameter("likeName");
		if(likeName == null || likeName.equals("")){
			consumptionQuery(request,response, recordServers);
			return ;
		}
		int start = (Integer.parseInt(page)-1)*Integer.parseInt(rows);
		List<RecordBean> likeQuery = recordServers.likeQuery(start, Integer.parseInt(rows), likeName);
		int likeCount = recordServers.likeCount(likeName);
		try {
			response.getWriter().print("{\"total\":"+likeCount+",\"rows\":"+ JSON.toJSONString(likeQuery)+"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
