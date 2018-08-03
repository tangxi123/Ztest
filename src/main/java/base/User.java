package base;

import java.io.Serializable;

public class User implements Serializable{
    private String userName;
    private String password;
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
}
