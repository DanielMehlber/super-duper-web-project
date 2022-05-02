package com.esports.manager.userManagement.exceptions;

/**
 * Sehr geehrter Herr Stiehl, bitte hören Sie auf das Frontend mit falschem Input umgehen zu wollen
 **/
public class InvalidInputException extends Exception {

    public InvalidInputException (String exMessage) {
        super(exMessage);
    }
}
