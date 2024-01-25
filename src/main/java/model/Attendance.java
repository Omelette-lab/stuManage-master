package model;

import java.util.Date;

public class Attendance {
    private String stu_id;
    private Date date;
    private String ifatt;

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIfatt(String ifatt) {
        this.ifatt = ifatt;
    }

    public String getStu_id() {
        return stu_id;
    }

    public Date getDate() {
        return date;
    }

    public String getIfatt() {
        return ifatt;
    }
}
