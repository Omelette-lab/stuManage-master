package model;
import java.io.Serializable;

public class Teacher extends User implements Serializable{
    private String id;
    String name;
    int cls_id;
    public String getId(){
        return id;
    }
    public int getCls_id(){return cls_id;}
    public void setId(String id) {
        this.id=id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCls_id(int cls_id) {
        this.cls_id = cls_id;
    }
}
