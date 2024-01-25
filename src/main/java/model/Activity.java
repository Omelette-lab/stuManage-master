package model;

import java.util.Date;

public class Activity {
    private int cls_id;
    private String name;
    private Date date;
    private String place;
    private String content;

    public void setCls_id(int cls_id) {
        this.cls_id = cls_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCls_id() {
        return cls_id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getPlace() {
        return place;
    }
}
