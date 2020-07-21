package xyz.ddxiong.travel.admin.pojo;

import java.io.Serializable;

/**
 * 收藏实体类
 */
public class Favorite implements Serializable {
    private int rid;
    private int uid;
    private String date;

    @Override
    public String toString() {
        return "Favorite{" +
                "rid=" + rid +
                ", uid=" + uid +
                ", date='" + date + '\'' +
                '}';
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
