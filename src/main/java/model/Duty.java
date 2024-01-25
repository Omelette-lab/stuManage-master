package model;

import java.util.Date;

public class Duty {
    private String stu_id;
    private Date date;
    private String content;

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStu_id() {
        return stu_id;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
