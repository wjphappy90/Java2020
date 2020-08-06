package cn.itcast.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class FindUserServlet
 */
public class FindUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("application/json;charset=utf-8");
        UserService us = new UserServiceImpl();
        //获得用户名参数
        String name = request.getParameter("name");
        if(name.equals("")){
            response.getWriter().write("");
        }else {
            //调用Service根据用户名获得用户列表数据
            List<User> list = us.findListByName(name);
            //将列表数据转换为json字符串
            ObjectMapper mapper = new ObjectMapper();
            //将json字符串发送到浏览器
            mapper.writeValue(response.getWriter(), list);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
