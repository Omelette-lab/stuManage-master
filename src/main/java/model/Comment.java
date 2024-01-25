package model;

import java.util.Date;

public class Comment {
    private String stu_id;
    private Date date;
    private String self_cmt;
    private String cls_adv;

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSelf_cmt(String self_cmt) {
        this.self_cmt = self_cmt;
    }

    public void setCls_adv(String cls_adv) {
        this.cls_adv = cls_adv;
    }

    public String getStu_id() {
        return stu_id;
    }

    public Date getDate() {
        return date;
    }

    public String getSelf_cmt() {
        return self_cmt;
    }

    public String getCls_adv() {
        return cls_adv;
    }

}
