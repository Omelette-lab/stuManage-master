package model;

import java.util.Date;

public class Summary {
    private String tea_id;
    private Date date;
    private String summary;

    public void setTea_id(String tea_id) {
        this.tea_id = tea_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTea_id() {
        return tea_id;
    }

    public Date getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }
}
