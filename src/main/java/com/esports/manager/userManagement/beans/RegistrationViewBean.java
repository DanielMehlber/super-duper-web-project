package com.esports.manager.userManagement.beans;

/**
 * Registration bean stored in session and used to generate view (jsp)
 * @author Maximilian Rublik
 */
public class RegistrationViewBean {

    private String errorMessage;

    public RegistrationViewBean() {}

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
