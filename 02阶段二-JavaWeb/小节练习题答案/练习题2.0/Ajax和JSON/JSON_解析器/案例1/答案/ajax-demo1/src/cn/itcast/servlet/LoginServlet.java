package cn.itcast.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoginServlet extends HttpServlet {

    /**
     * @author lyd
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //准备放置登陆结果信息的map,最后会将map转换为json字符串
        Map result = new HashMap();
        // 1 获得用户名
        String name = request.getParameter("name");
        // 2 验证用户名不为空.
        if (name == null || name.trim().equals("")) {
            //如果为空=> 在map中添加错误信息
            //0代表失败,作为前台判断依据
            result.put("type", 0);
            //添加状态描述信息
            result.put("msg", "用户名不能为空");
        } else {
            //1代表成功
            result.put("type", 1);
            //添加状态描述信息
            result.put("msg", "欢迎您回来!" + name);
        }
        // 3 将结果转换为json字符串输出到浏览器
        //{"type":1,"msg":"欢迎您回来!tom"}
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        //并且传递给客户端
        mapper.writeValue(response.getWriter(),result);
    }

    /**
     * @author lyd
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
