package xyz.ddxiong.travel.admin.web;

import xyz.ddxiong.travel.admin.common.BaseServlet;
import xyz.ddxiong.travel.admin.pojo.Route;
import xyz.ddxiong.travel.admin.service.RouteService;
import xyz.ddxiong.travel.admin.utils.BeanFactory;
import xyz.ddxiong.travel.admin.utils.MybatisUtils;
import xyz.ddxiong.travel.admin.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月21日 19:00:00
 * @Description: TODO
 */
@WebServlet("/routeServlet")
public class RouteServlet extends BaseServlet {

    public void findRouteByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 8;
        int pageNumber = 1;
        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (NumberFormatException e) {
        }
        try {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        } catch (NumberFormatException e) {
        }
        RouteService routeService = (RouteService) BeanFactory.getBean("RouteService");
        PageBean<Route> routeByPage = routeService.findRouteByPage(pageSize, pageNumber);
        /**
         * 请求转发到线路详情页面
         */
        request.setAttribute("pageBean",routeByPage);
        request.getRequestDispatcher("/pages/route/route_list.jsp").forward(request,response);
    }

}