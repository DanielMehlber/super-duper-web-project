package com.esports.manager.userManagement.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.esports.manager.userManagement.entities.User;

public class LoginSessionBean implements Serializable {
    private static final long servialVersionUID = 1L;
    
    private User user;
    //To track the login time of the user
    private LocalDateTime loginTime;

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public LocalDateTime getDateTime(){
        return loginTime;
    }
    public void setDateTime(LocalDateTime loginTime){
        this.loginTime = loginTime;
    }
}
