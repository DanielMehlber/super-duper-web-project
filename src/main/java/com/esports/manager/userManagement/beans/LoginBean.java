package com.esports.manager.userManagement.beans;

import java.io.Serializable;

public class LoginBean implements Serializable {
    private static final long servialVersionUID = 1L;
    
    private Long id;
    private String username;
    private String password;

    public Long getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setUsername(String username){
       this.username = username; 
    }
    public void setPassword(String password){
        this.password = password;
    }
}
