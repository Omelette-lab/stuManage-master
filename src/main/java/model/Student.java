package model;

import java.sql.Date;

public class Student {
    private String id;
    private String name;
    private int age;
    private String sex;
    private String ethnicity;//民族
    private String poli_status;//政治面貌
    private String position;
    private int cls_id; //班级号
    private String dor_id;  //宿舍号

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPoli_status() {
        return poli_status;
    }

    public String getPosition() {
        return position;
    }

    public String getSex() {
        return sex;
    }

    public int getCls_id(){
        return cls_id;
    }

    public String getDor_id(){
        return dor_id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPoli_status(String poli_status) {
        this.poli_status = poli_status;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCls_id(int cls_id){
        this.cls_id = cls_id;
    }

    public void setDor_id(String dor_id){
        this.dor_id = dor_id;
    }
}
