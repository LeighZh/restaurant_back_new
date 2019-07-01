package com.znsd.restaurant.servlet;

import com.alibaba.fastjson.JSON;
import com.znsd.restaurant.bean.TableZibean;
import com.znsd.restaurant.servers.TableServers;
import com.znsd.restaurant.servers.impl.TableServersImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class TableServlet
 */
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableServlet() {
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
		String mark = request.getParameter("mark");
		TableServers tab = new TableServersImpl();
		if(mark.equals("a")){
			String page = request.getParameter("page");
			String pageSize = request.getParameter("rows");
			List<TableZibean> list = tab.main(page,pageSize);
			String count = (String) tab.count();
			response.getWriter().write("{\"total\":"+count+",\"rows\":"+ JSON.toJSONString(list)+"}");
			
		}else if(mark.equals("add")){
			String id = request.getParameter("id");
			String userName = request.getParameter("userName");
			tab.add(id,userName);
		}else if(mark.equals("delete")){
			String id = request.getParameter("id");
			String userName = request.getParameter("userName");
			tab.delete(id,userName);
		}else if(mark.equals("b")){
			String page = request.getParameter("page");
			String pageSize = request.getParameter("rows");
			List<TableZibean> list = tab.main2(page,pageSize);
			String count = (String) tab.count();
			response.getWriter().write("{\"total\":"+count+",\"rows\":"+ JSON.toJSONString(list)+"}");
		}else if(mark.equals("remove")){
			System.out.println("remove");
			String id = request.getParameter("id");
			String userName = request.getParameter("userName");
			tab.remove(id,userName);
		}else if(mark.equals("board")){
			String name = request.getParameter("userName");
			String id = request.getParameter("id");
			tab.board(name,id);
		}else if(mark.equals("increase")){
			System.out.println("increase");
			String name = request.getParameter("name");
			String line = request.getParameter("line");
			String row = request.getParameter("row");
			System.out.println("name"+name+" line"+line+" row"+row);
			tab.increase(name,line,row);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
