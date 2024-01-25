package model;

import java.util.Date;

public class Meetings {
    int cls_id;
    Date date;
    String theme;
    String content;

    public void setCls_id(int cls_id) {
        this.cls_id = cls_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCls_id() {
        return cls_id;
    }

    public Date getDate() {
        return date;
    }

    public String getTheme() {
        return theme;
    }

    public String getContent() {
        return content;
    }
}
