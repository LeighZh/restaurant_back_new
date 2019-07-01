package com.znsd.restaurant.servlet.System;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class SessionServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("appliaction/JSON;charsex=UTF-8");
        System.out.println("sessionServlet");
        HttpSession session = request.getSession();
        Enumeration<String> attrs = session.getAttributeNames();
        while (attrs.hasMoreElements()) {
            // 获取session键值
            String name = attrs.nextElement().toString();
            // 根据键值取session中的值
            Object vakue = session.getAttribute(name);
            // 打印结果
            System.out.println("------" + name + ":" + vakue + "--------\n");
        }

//        String name = (String)session.getAttribute("name");
//
//        PrintWriter out;
//        try {
//            String userName = ((UserInfo) ActionContext.getContext()
//                    .getSession().get("name")).getUserInfoName();
//            System.out.println(userName);
//            out = response.getWriter();
//            out.print(userName);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
