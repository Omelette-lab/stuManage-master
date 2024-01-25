package model;
import java.io.Serializable;

public class User implements Serializable{
    String password;
    String email;



    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password=password;
    }
}
