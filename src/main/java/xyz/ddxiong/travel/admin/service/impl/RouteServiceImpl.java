package xyz.ddxiong.travel.admin.service.impl;

import xyz.ddxiong.travel.admin.dao.RouteMapper;
import xyz.ddxiong.travel.admin.pojo.Category;
import xyz.ddxiong.travel.admin.pojo.Route;
import xyz.ddxiong.travel.admin.service.RouteService;
import xyz.ddxiong.travel.admin.utils.DaoFactory;
import xyz.ddxiong.travel.admin.utils.PageBean;
import xyz.ddxiong.travel.admin.utils.PageUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月21日 19:06:00
 * @Description: TODO
 */
public class RouteServiceImpl implements RouteService {
    @Override
    public PageBean<Route> findRouteByPage(int pageSize, int pageNumber) {
        RouteMapper routeMapper = DaoFactory.getBean(RouteMapper.class);
        int totalCount = routeMapper.findTotalCount();
        PageBean<Route> routePageBean = new PageBean<>(pageSize, totalCount);
        routePageBean.setPageNumber(pageNumber);
        int startIndex = (pageNumber - 1) * pageSize;
        List<Route> routeByPage = routeMapper.findRouteByPage(startIndex, pageSize);
        /**
         * 封装信息
         */
        routePageBean.setData(routeByPage);
        /**
         * 计算起始页和尾页
         */
        int[] pagination = PageUtils.pagination(pageNumber, routePageBean.getTotalPage());
        routePageBean.setStart(pagination[0]);
        routePageBean.setEnd(pagination[1]);
        return routePageBean;
    }

    @Override
    public Route findRouteByRid(String rid) {
        RouteMapper mapper = DaoFactory.getBean(RouteMapper.class);
        Route routeByRid = mapper.findRouteByRid(rid);
        return routeByRid;
    }

    @Override
    public List<Category> findAllCategory() {
        RouteMapper mapper = DaoFactory.getBean(RouteMapper.class);
        List<Category> allCategory = mapper.findAllCategory();
        return allCategory;
    }

    @Override
    public Boolean updateRouteByRid(Route route) {
        RouteMapper mapper = DaoFactory.getBean(RouteMapper.class);
        int i = mapper.updateRouteByRid(route);
        return i > 0;
    }

    @Override
    public void delRouteByRid(String rid) {
        RouteMapper mapper = DaoFactory.getBean(RouteMapper.class);
        mapper.delRouteByRid(rid);
    }

    @Override
    public void delRouteByRids(String[] rids) {
        RouteMapper mapper = DaoFactory.getBean(RouteMapper.class);
        for (String rid : rids) {
            mapper.delRouteByRid(rid);
        }
    }

    @Override
    public void addRoute(Route route) {
        RouteMapper mapper = DaoFactory.getBean(RouteMapper.class);
        /**
         * 补充数据信息
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        route.setRdate(format);
        route.setIsThemeTour("0");
        route.setCount(0);
        route.setSid(1);
        mapper.addRoute(route);
    }
}
