package model;
import java.io.Serializable;

public class Admin extends User implements Serializable{
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username=name;
    }
}
