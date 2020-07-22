<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Starter</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/adminLTE/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/adminLTE/css/skins/skin-blue.min.css">
</head>
<body>
<div id="frameContent" class="content" style="margin-left:0px;padding-top: 0px;">
    <!-- 标题 -->
    <section class="content-header">
        <h1>
            线路查询
            <small>itheima route query</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="index.html"><i class="fa fa-dashboard"></i>首页</a></li>
            <li class="active">线路管理</li>
        </ol>
    </section>

    <!-- 内容 -->
    <section class="content container-fluid">
<div class="row">
    <div class="box box-info" style="padding-left: 0px;">
        <!--搜索-->
        <br>
        <form role="form">
            <div class="box-body">
                <div class="form-group">
                    <div class="col-md-6" style="padding-left: 0px;">
                        <input type="text" class="form-control" id="rname" name="rname"  placeholder="请输入线路名字">
                    </div>
                    <div class="col-md-1">
                        <button type="submit" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
                                class="ion-search"></i>查询&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </button>
                    </div>
                    <div class="col-md-5">
                        &nbsp;&nbsp;
                        <button onclick="checkAll()"type="button" class="btn btn-danger">&nbsp;&nbsp;&nbsp;&nbsp;<i
                                class="fa fa-fw fa-remove"></i>批量删除&nbsp;&nbsp;&nbsp;&nbsp;
                        </button>
                        <script>
                            /**
                             * 当点击时发送请求
                             * */
                            function checkAll() {
                                var objForm = document.getElementById("listForm")
                                objForm.submit();
                            }
                        </script>
                    </div>
                </div>
            </div>
            <!-- /.box-body -->
        </form>


        <!-- /.box-header -->
        <div class="box-body">
            <div id="example2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                <div class="row">
                    <div class="col-sm-6"></div>
                    <div class="col-sm-6"></div>
                </div>
                <div class="row">
				    <form action="/routeServlet" method="post" id="listForm">
                        <input type="hidden" name="action" value="delCheckedRids">
                    <table id="example2" class="table table-bordered table-hover dataTable"
                           role="grid" aria-describedby="example2_info">
						<colgroup>
                            <col width="5%">
                            <col width="5%">
                            <col width="10%">
                            <col width="45%">
                            <col width="10%">
                            <col width="10%">
                            <col width="20%">
                        </colgroup>
                        <thead>
                        <tr role="row">
                            <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                colspan="1"
                                aria-label="Rendering engine: activate to sort column ascending">
                                <input type="checkbox" id="checkAll">勾选
                            </th>
                            <th class="sorting_asc" tabindex="0" aria-controls="example2"
                                rowspan="1" colspan="1"
                                aria-label="Browser: activate to sort column descending"
                                aria-sort="ascending">编号
                            </th>
                            <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                colspan="1"
                                aria-label="Platform(s): activate to sort column ascending">
                                图片
                            </th>
                            <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                colspan="1"
                                aria-label="Engine version: activate to sort column ascending">
                                线路名称
                            </th>
                            <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                colspan="1"
                                aria-label="CSS grade: activate to sort column ascending">线路价格
                            </th>
                            <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                colspan="1"
                                aria-label="CSS grade: activate to sort column ascending">收藏数量
                            </th>
                            <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1"
                                colspan="1"
                                aria-label="CSS grade: activate to sort column ascending">操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.data}" var="route">
                        <tr role="row">
                            <td class=""><input type="checkbox" name="rids" value="${route.rid}"></td>
                            <td class="sorting_1">${route.rid}</td>
                            <td>
                                <img
                                    src="${ctx}${route.rimage}"
                                    width="99px"></td>
                            <td>${route.rname}</td>
                            <td>${route.price}</td>
                            <td>${route.count}</td>
                            <td>
                                <a class="btn btn-primary" href="/routeServlet?action=updateRoute&rid=${route.rid}" target="iframe"><i
                                        class="fa fa-fw fa-edit"></i> 修改</a>
                                <a class="btn btn-danger" href="javascript:delRoute(${route.rid})"><i
                                        class="fa fa-fw fa-remove"></i> 删除</a>
                                <script>
                                    function delRoute(rid) {
                                        if (confirm("确定要删除吗?")) {
                                            location.href="routeServlet?action=delRoute&rid="+rid
                                        }
                                    }
                                </script>
                            </td>
                            </c:forEach>
                        </tr>
                        <%--<tr role="row">
                            <td class=""><input type="checkbox" name="rid"></td>
                            <td class="sorting_1">2</td>
                            <td><img
                                    src="${ctx}/img/product/small/m35c17b5b73d618bbdd2abe231f1307838.jpg"
                                    width="99px"></td>
                            <td>【¥1099秒杀 约惠华东五市+乌镇+木渎+灵山大佛 双飞6天 无锡进上海出】升级2晚豪华酒店</td>
                            <td>1099.0</td>
                            <td>1000</td>
                            <td>
                                <a class="btn btn-primary" href="route_update.jsp"><span
                                        class="fa fa-fw fa-edit"></span> 修改</a>
                                <a class="btn btn-danger" href="javascript:delRoute(7)"><i
                                        class="fa fa-fw fa-remove"></i> 删除</a>
                            </td>
                        </tr>
                        <tr role="row">
                            <td class=""><input type="checkbox" name="rid"></td>
                            <td class="sorting_1">1</td>
                            <td><img
                                    src="${ctx}/img/product/small/m35c17b5b73d618bbdd2abe231f1307838.jpg"
                                    width="99px"></td>
                            <td>【¥1099秒杀 约惠华东五市+乌镇+木渎+灵山大佛 双飞6天 无锡进上海出】升级2晚豪华酒店</td>
                            <td>1099.0</td>
                            <td>1000</td>
                            <td>
                                <a class="btn btn-primary" href="route_update.jsp"><i
                                        class="fa fa-fw fa-edit"></i> 修改</a>
                                <a class="btn btn-danger" href="javascript:delRoute(7)"><i
                                        class="fa fa-fw fa-remove"></i> 删除</a>
                            </td>
                        </tr>
                        <tr role="row">
                            <td class=""><input type="checkbox" name="rid"></td>
                            <td class="sorting_1">2</td>
                            <td><img
                                    src="${ctx}/img/product/small/m35c17b5b73d618bbdd2abe231f1307838.jpg"
                                    width="99px"></td>
                            <td>【¥1099秒杀 约惠华东五市+乌镇+木渎+灵山大佛 双飞6天 无锡进上海出】升级2晚豪华酒店</td>
                            <td>1099.0</td>
                            <td>1000</td>
                            <td>
                                <a class="btn btn-primary" href="route_update.jsp"><span
                                        class="fa fa-fw fa-edit"></span> 修改</a>
                                <a class="btn btn-danger" href="javascript:delRoute(7)"><i
                                        class="fa fa-fw fa-remove"></i> 删除</a>
                            </td>
                        </tr>
                        <tr role="row">
                            <td class=""><input type="checkbox" name="rid"></td>
                            <td class="sorting_1">1</td>
                            <td><img
                                    src="${ctx}/img/product/small/m35c17b5b73d618bbdd2abe231f1307838.jpg"
                                    width="99px"></td>
                            <td>【¥1099秒杀 约惠华东五市+乌镇+木渎+灵山大佛 双飞6天 无锡进上海出】升级2晚豪华酒店</td>
                            <td>1099.0</td>
                            <td>1000</td>
                            <td>
                                <a class="btn btn-primary" href="route_update.jsp"><i
                                        class="fa fa-fw fa-edit"></i> 修改</a>
                                <a class="btn btn-danger" href="javascript:delRoute(7)"><i
                                        class="fa fa-fw fa-remove"></i> 删除</a>
                            </td>
                        </tr>
                        <tr role="row">
                            <td class=""><input type="checkbox" name="rid"></td>
                            <td class="sorting_1">2</td>
                            <td><img
                                    src="${ctx}/img/product/small/m35c17b5b73d618bbdd2abe231f1307838.jpg"
                                    width="99px"></td>
                            <td>【¥1099秒杀 约惠华东五市+乌镇+木渎+灵山大佛 双飞6天 无锡进上海出】升级2晚豪华酒店</td>
                            <td>1099.0</td>
                            <td>1000</td>
                            <td>
                                <a class="btn btn-primary" href="route_update.jsp"><span
                                        class="fa fa-fw fa-edit"></span> 修改</a>
                                <a class="btn btn-danger" href="javascript:delRoute(7)"><i
                                        class="fa fa-fw fa-remove"></i> 删除</a>
                            </td>
                        </tr>--%>
                        </tbody>

                    </table>
					</form>
                </div>

                <%--显示总条数--%>
                <div class="row">
                    <div class="col-sm-5">
                        <div class="dataTables_info" id="example2_info" role="status"
                             aria-live="polite">共${pageBean.totalPage}页，共${pageBean.totalCount}条
                        </div>
                    </div>
                    <div class="col-sm-7">
                        <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
                            <ul class="pagination">
                                <%--首页--%>
                                <li class="paginate_button previous" id="example2_firsts"><a
                                        href="/routeServlet?action=findRouteByPage&pageSize=${pageBean.pageSize}&pageNumber=1" aria-controls="example2" data-dt-idx="0" tabindex="0">首页</a>
                                </li>
                                <%--上一页--%>
                                <c:if test="${pageBean.pageNumber>1}">
                                <li class="paginate_button previous" id="example2_previous"><a
                                        href="/routeServlet?action=findRouteByPage&pageSize=${pageBean.pageSize}&pageNumber=${pageBean.pageNumber-1}" aria-controls="example2" data-dt-idx="0" tabindex="0">上一页</a>
                                </li>
                                </c:if>
                                <%--中间也显示--%>
                                <c:forEach begin="${pageBean.start}" end="${pageBean.end}" step="1" var="i">
                                    <c:if test="${pageBean.pageNumber==i}">
                                <li class="paginate_button active"><a href="/routeServlet?action=findRouteByPage&pageSize=${pageBean.pageSize}&pageNumber=${i}" aria-controls="example2"
                                                                      data-dt-idx="1" tabindex="0">${i}</a>
                                </li>
                                    </c:if>
                                    <c:if test="${pageBean.pageNumber!=i}">
                                        <li class="paginate_button "><a href="/routeServlet?action=findRouteByPage&pageSize=${pageBean.pageSize}&pageNumber=${i}" aria-controls="example2"
                                                                              data-dt-idx="1" tabindex="0">${i}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                    <%--下一页--%>
                                    <c:if test="${pageBean.pageNumber<pageBean.totalPage}">

                                    <li class="paginate_button next" id="example2_next"><a href="/routeServlet?action=findRouteByPage&pageSize=${pageBean.pageSize}&pageNumber=${pageBean.pageNumber+1}"
                                                                                       aria-controls="example2"
                                                                                       data-dt-idx="7"
                                                                                       tabindex="0">下一页</a>
                                </li>
                                    </c:if>
                                    <%--尾页--%>
                                <li class="paginate_button next" id="example2_last"><a href="/routeServlet?action=findRouteByPage&pageSize=${pageBean.pageSize}&pageNumber=${pageBean.totalPage}"
                                                                                       aria-controls="example2"
                                                                                       data-dt-idx="7"
                                                                                       tabindex="0">尾页</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.box-body -->
    </div>
</div>
<!-- /.row -->
</section>
    <!-- /内容 -->
</div>
<script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${ctx}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/plugins/adminLTE/js/adminlte.js"></script>
<script src="${ctx}/plugins/common.js"></script>
<script>
    $("#checkAll").click(function () {
        var flag = $(this).prop("checked");
        $("input[name='rids']").prop("checked",flag);
    });
</script>
</body>
</html>