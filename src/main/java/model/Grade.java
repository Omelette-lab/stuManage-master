package model;

import java.util.Date;

public class Grade {
    private String stu_id;
    private String subject;
    private float chinse;
    private float math;
    private float english;
    private Date date;

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setChinse(float chinse) {
        this.chinse = chinse;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public void setEnglish(float english) {
        this.english = english;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStu_id() {
        return stu_id;
    }

    public String getSubject() {
        return subject;
    }

    public float getChinse() {
        return chinse;
    }

    public float getMath() {
        return math;
    }

    public float getEnglish() {
        return english;
    }

    public Date getDate() {
        return date;
    }
}
