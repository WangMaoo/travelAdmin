package xyz.ddxiong.travel.admin.common;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 不需要添加任何访问路径
 * 因为此类我们不希望外界直接访问
 */
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获取请求标识(请求标识就是要执行的方法的名称)
            String action = request.getParameter("action");
            //2.获取调用者类的字节码对象
            Class clazz = this.getClass();
            //3.获取要执行的方法的字节码对象 (获取公共的方法的字节码)
            Method method = clazz.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //4.反射执行方法
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("反射调用方法异常");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}