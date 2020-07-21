package xyz.ddxiong.travel.admin.service;

import xyz.ddxiong.travel.admin.pojo.Route;
import xyz.ddxiong.travel.admin.utils.PageBean;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月21日 19:05:00
 * @Description: TODO
 */
public interface RouteService {
    /**
     * 分页查询所有线路
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageBean<Route> findRouteByPage(int pageSize, int pageNumber);
}
