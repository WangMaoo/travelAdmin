package xyz.ddxiong.travel.admin.utils;

import java.util.List;

/**
 * 分页查询时,封装浏览器需要的数据信息
 * @param <T>
 */
public class PageBean<T> {
    private List<T> data; //本页需要展示的数据信息
    private int pageNumber; // 当前页页码
    private int pageSize; // 每页显示条数
    private int totalCount; // 总条数
    private int totalPage; // 总页数(需要计算)

    private int start; // 开始页码
    private int end; // 结束页码

    @Override
    public String toString() {
        return "PageBean{" +
                "data=" + data +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + getTotalPage() +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * 此有参构造为的是保证这两个属性一定有值,
     * 这样以来计算总页数的get方法才有意义
     * @param pageSize
     * @param totalCount
     */
    public PageBean(int pageSize, int totalCount) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 进行简单的业务处理
     * @return
     */
    public int getTotalPage() {
        // 计算总页数
        return (int)Math.ceil(totalCount * 1.0 / pageSize);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
