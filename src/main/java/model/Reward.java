package model;

import java.util.Date;

public class Reward {
    private String stu_id;
    private Date date;
    private char type;
    private String reason;

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStu_id() {
        return stu_id;
    }

    public Date getDate() {
        return date;
    }

    public char getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }
}
