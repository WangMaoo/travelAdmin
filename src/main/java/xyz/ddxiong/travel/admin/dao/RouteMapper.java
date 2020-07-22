package xyz.ddxiong.travel.admin.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.ddxiong.travel.admin.pojo.Category;
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

    /**
     * 根据rid查询线路
     * @param rid
     * @return
     */
    @Select("select * from tab_route where rid = #{rid}")
    Route findRouteByRid(String rid);

    /**
     * 查询所有分类线路
     * @return
     */
    @Select("select * from tab_category")
    List<Category> findAllCategory();

    /**
     * 更新线路
     * @param route
     * @return
     */
    @Update("update tab_route set rname=#{rname},price=#{price},routeIntroduce=#{routeIntroduce},rflag=#{rflag},cid=#{cid} where rid=#{rid}")
    int updateRouteByRid(Route route);

    /**
     * 根据rid删除线路
     * @param rid
     */
    @Delete("delete from tab_route where rid = #{rid}")
    void delRouteByRid(String rid);
}
