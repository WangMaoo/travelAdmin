package xyz.ddxiong.travel.admin.service;

import xyz.ddxiong.travel.admin.pojo.Category;
import xyz.ddxiong.travel.admin.pojo.Route;
import xyz.ddxiong.travel.admin.utils.PageBean;

import java.util.List;

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

    /**
     * 根据rid查询线路
     * @param rid
     * @return
     */
    Route findRouteByRid(String rid);

    /**
     * 查询线路类别
     *
     * @return
     */
    List<Category> findAllCategory();

    /**
     * 更新线路
     * @param route
     * @return
     */
    Boolean updateRouteByRid(Route route);

    /**
     * 根据rid删除线路
     * @param rid
     */
    void delRouteByRid(String rid);
}
