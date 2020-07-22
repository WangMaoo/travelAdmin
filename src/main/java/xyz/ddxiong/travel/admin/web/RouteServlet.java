package xyz.ddxiong.travel.admin.web;

import org.apache.commons.beanutils.BeanUtils;
import xyz.ddxiong.travel.admin.common.BaseServlet;
import xyz.ddxiong.travel.admin.pojo.Category;
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
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

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
        request.setAttribute("pageBean", routeByPage);
        request.getRequestDispatcher("/pages/route/route_list.jsp").forward(request, response);
    }

    public void updateRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        RouteService routeService = (RouteService) BeanFactory.getBean("RouteService");
        Route routeByRid = routeService.findRouteByRid(rid);
        /**
         * 查询所有分类线路
         */
        List<Category> allCategory = routeService.findAllCategory();
        request.getSession().setAttribute("allCategory", allCategory);
        /**
         * 设置session,请求转发
         */
        request.getSession().setAttribute("route", routeByRid);
        request.getRequestDispatcher("/pages/route/route_update.jsp").forward(request, response);
    }

    public void updateRouteByRid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            /**
             * 首先获取数据进行封装
             */
            Map<String, String[]> parameterMap = request.getParameterMap();
            Route route = new Route();
            BeanUtils.populate(route, parameterMap);
            RouteService routeService = (RouteService) BeanFactory.getBean("RouteService");
            Boolean flag = routeService.updateRouteByRid(route);
            if (flag) {
                response.sendRedirect(request.getContextPath() + "/routeServlet?action=findRouteByPage");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        RouteService routeService = (RouteService) BeanFactory.getBean("RouteService");
        routeService.delRouteByRid(rid);
        response.sendRedirect(request.getContextPath() + "/routeServlet?action=findRouteByPage");
    }

}