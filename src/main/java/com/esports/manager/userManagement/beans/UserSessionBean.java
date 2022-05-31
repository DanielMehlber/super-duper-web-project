package com.esports.manager.userManagement.beans;

import com.esports.manager.userManagement.entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This session bean stores login data used for authentication in session.
 *
 * @author Philipp Phan
 */
public class UserSessionBean implements Serializable {
    private static final long servialVersionUID = 1L;
    public UserSessionBean(){}

    private User user;
    //To track the login time of the user
    private LocalDateTime loginTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return loginTime;
    }

    public void setDateTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}
