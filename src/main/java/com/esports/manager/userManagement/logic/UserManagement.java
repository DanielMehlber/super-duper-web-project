package com.esports.manager.userManagement.logic;

import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * collection of methods that will be used in user management
 *
 * @author Daniel Mehlber,
 */
public class UserManagement {

    private static Logger log = LogManager.getLogger(UserManagement.class);

    public static void registerUser(String username, String password, String email) throws InvalidInputException {
        if (checkUserInput(username, password, email)) {
            // TODO: Create a new user element and persist it to the db
            // UserRepository.createNewUser();
        }
        else {
            throw new InvalidInputException("registerUser: Invalid Input");
        }
    }

    private static boolean checkUserInput(String username, String password, String email) {
        // TODO: Check for username correctness
        return isValidUsername(username) && isValidPassword(password) && isValidEmailAddress(email);
    }

    private static boolean isValidUsername(String username) {
        if (username.toLowerCase().contains("susi") || username.toLowerCase().contains("habicht")){
            return false;
        }

        // check whether the username already exists in the database
        return UserRepository.isUniqueUsername(username);
    }

    private static boolean isValidPassword(String password) {
        // Check for use of letters
        Pattern letters = Pattern.compile("[a-zA-Z]]");

        // Check for user of special characters, which are = {!, ?, #, %, @}
        Pattern special = Pattern.compile("[!?#&%@]]");

        // We want the password to be at least 6 characters long + match the patterns above
        return password.length() > 5 && letters.matcher(password).find() && special.matcher(password).find();
    }


    /**
     * copied from <a href="https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-">stackoverflow</a>
     * since we don't want to implement our own check whether an email is correct, nor use some alien like regex
     * language. So we take what the java god gave us
     *
     * @param email
     * @return boolean whether the email address is vaild in its format-
     */
    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
