package model;

import java.util.Date;

public class Fund {
    private int cls_id;
    private Date date;
    private float amount;
    private float balance;
    private String remark;

    public void setCls_id(int cls_id) {
        this.cls_id = cls_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCls_id() {
        return cls_id;
    }

    public Date getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public float getBalance() {
        return balance;
    }

    public String getRemark() {
        return remark;
    }
}
