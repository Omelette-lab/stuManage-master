package model;

import java.sql.Timestamp;

public class Plan {
    private String tea_id;
    private Timestamp time;
    private String plan;

    public void setTea_id(String tea_id) {
        this.tea_id = tea_id;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTea_id() {
        return tea_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getPlan() {
        return plan;
    }
}
