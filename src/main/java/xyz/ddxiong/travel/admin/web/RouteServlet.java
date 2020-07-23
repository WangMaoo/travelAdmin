package xyz.ddxiong.travel.admin.web;

import org.apache.commons.beanutils.BeanUtils;
import xyz.ddxiong.travel.admin.common.BaseServlet;
import xyz.ddxiong.travel.admin.pojo.Category;
import xyz.ddxiong.travel.admin.pojo.Route;
import xyz.ddxiong.travel.admin.service.RouteService;
import xyz.ddxiong.travel.admin.utils.BeanFactory;
import xyz.ddxiong.travel.admin.utils.MybatisUtils;
import xyz.ddxiong.travel.admin.utils.PageBean;
import xyz.ddxiong.travel.admin.utils.UuidUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
@MultipartConfig
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

    public void delCheckedRids(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] rids = request.getParameterValues("rids");
        RouteService routeService = (RouteService) BeanFactory.getBean("RouteService");
        routeService.delRouteByRids(rids);
        response.sendRedirect(request.getContextPath() + "/routeServlet?action=findRouteByPage");
    }

    public void addRoute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RouteService routeService = (RouteService) BeanFactory.getBean("RouteService");
        List<Category> allCategory = routeService.findAllCategory();
        request.getSession().setAttribute("allCategory", allCategory);
        request.getRequestDispatcher("/pages/route/route_add.jsp").forward(request, response);
    }

    public void addRouteImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //------------------------------解析文件上传项携带的数据信息
            // 获取文件上传项 对象
            Part filePart = request.getPart("rimage");
            // 获取上传的文件的名称
            String filename = filePart.getSubmittedFileName();
            //获取资源在服务器上的绝对路径
            //ServletContext servletContext = request.getServletContext();
            //String fileUrl =servletContext.getRealPath("/img1/");
            //System.out.println(fileUrl);
            //======================
            String fileUrl = "D:/imgs/";
            // 生成唯一的图片名称
            String uuid = UuidUtils.getUuid();
            filename = uuid + filename;
            // 获取文件的流信息,保存到指定的文件中
            filePart.write(fileUrl+filename);
            // 清理缓存
            filePart.delete();
            //------------------------------添加表单中的普通表单项携带的数据信息
            //1.获取请求携带的数据信息
            Map map = request.getParameterMap();
            Route route = new Route();
            // 将图片路径存放到数据库中
            //route.setRimage("img1/"+filename);

            route.setRimage("http://localhost:80/"+filename);
            // 将请求携带的数据封装到route实体中
            BeanUtils.populate(route,map);
            //2.调用service处理业务逻辑
            RouteService service = (RouteService)BeanFactory.getBean("RouteService");
            service.addRoute(route);
            //3.重定向查询所有线路信息
            response.sendRedirect(request.getContextPath()+"/routeServlet?action=findRouteByPage");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}