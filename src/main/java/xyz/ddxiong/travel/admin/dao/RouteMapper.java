package xyz.ddxiong.travel.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.ddxiong.travel.admin.pojo.Route;

import java.util.List;

/**
 * @author Wang
 * @version 1.0.0
 * @createTime 2020年07月21日 19:08:00
 * @Description: TODO
 */
public interface RouteMapper {

    /**
     * 查询所有的线路数量
     * @return
     */
    @Select("select count(*) from tab_route")
    int findTotalCount();

    /**
     * 分页查询所有线路信息
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Select("select * from tab_route limit #{startIndex},#{pageSize}")
    List<Route> findRouteByPage(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
}
