package com.esports.manager.userManagement.logic;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.beans.LoginSessionBean;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;

import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.UserAlreadyExistingException;

import jakarta.servlet.http.HttpSession;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
import com.esports.manager.userManagement.exceptions.UsernameAlreadyTakenException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * collection of methods that will be used in user management
 *
 * @author Daniel Mehlber,
 */
public class UserManagement {

    private static final Logger log = LogManager.getLogger(UserManagement.class);

    /**
     * Checks if the passed username is available. In order to do so, it attempts to
     * fetch a user with this
     * username and checks if the operation was successful.
     * 
     * @param username username to check
     * @return true if the username is available
     * @throws InternalErrorException some unexpected and fatal internal error
     *                                occurred
     * @author Daniel Mehlber
     * @see UserManagement#fetchUserByUsername(String)
     */
    public static boolean isUsernameAvailable(final String username) throws InternalErrorException {
        log.debug("checking if username is available...");
        try {
            // attempt to fetch user with this username
            fetchUserByUsername(username);

            log.debug("username check complete: username is already taken and unavailable");

            // user fetched successfully, so the username is already taken
            return false;
        } catch (NoSuchUserException e) {
            // no user with this username found, so the username is available
            log.debug("username check complete: username is available");
            return true;
        }
    }

    public static User fetchUserByUsername(final String username) throws NoSuchUserException, InternalErrorException {
        log.debug("fetching user by username...");
        User user = UserRepository.getByUsername(username);
        return user;
    }


    // TODO: Log user out of active session
    public static void performLogout() {

    }

    /**
     * Checks if username and password (from html form) align with those in database
     *
     * @param username passed username by unauthenticated user
     * @param password passed password by unauthenticated user.
     * @param session session in which the user will be logged in if username and password are correct
     * @throws InternalErrorException an unexpected and fatal internal error occurred
     * @throws NoSuchUserException a user with passed username was not found in database
     * @author Philipp Phan
     */
    public static void performLogin(String username, String password, HttpSession session)
            throws InternalErrorException, NoSuchUserException {
        try {
            User user = UserRepository.getByUsername(username);
            if (username.equals(user.getUsername()) && password.equals(user.getPasswordHash())) {
                // Create sessionBean
                LoginSessionBean loginSessionBean = new LoginSessionBean();

                // Insert user object inside sessionBean
                loginSessionBean.setUser(UserRepository.getByUsername(username));

                // Insert sessionBean in HttpSession
                session.setAttribute("loginSessionBean", loginSessionBean);
                log.info("Login succcessfull. Welcome");
            } else {
                log.warn("LOGIN NOT SUCCESSFULL !");
            }
        } catch (InternalErrorException e) {
            log.error("Internal Error occured while login", e);
            throw e;
        } catch (NoSuchUserException e) {
            log.error("No user with username found", e);
            throw e;
        }
    }


    /**
     * Perform registration with passed user data after it has been checked and verified.
     * @param username requested username for registration (must be available)
     * @param password requested password for registration (must meet certain criteria)
     * @param email requested email for registration (must be unique in database)
     * @throws InvalidInputException the passed input is syntactically invalid or not allowed
     * @throws InternalErrorException an unexpected internal error occurred
     * @throws UsernameAlreadyTakenException the requested username is not available
     * @author Maximilian Rublik
     * @throws UserAlreadyExistingException 
     */
    public static void registerUser(String username, String password, String email) throws InvalidInputException, InternalErrorException, UsernameAlreadyTakenException, UserAlreadyExistingException {
        if (checkPassedUserData(username, password, email)) {
            // create and persist new user
            User newUser = new User(username, email, hashPassword(password));
            UserRepository.createNewUser(newUser);
        }
        else {
            throw new InvalidInputException("registerUser: Invalid Input");
        }
    }

    /**
     * Checks received user data for syntactical validity
     * @param username passed username
     * @param password passed password
     * @param email passed email address
     * @return true, if all user data is valid
     * @throws InternalErrorException username uniqueness check failed due to an internal error
     * @author Maximilian Rublik
     * @throws UserAlreadyExistingException 
     */
    private static boolean checkPassedUserData(String username, String password, String email) throws InternalErrorException, UserAlreadyExistingException {
        return isValidUsername(username) && isValidPassword(password) && isValidEmailAddress(email);
    }

    /**
     * Checks received username for syntactical validity and uniqueness
     * @param username passed username
     * @return true if username is valid and allowed
     * @throws InternalErrorException uniqueness check failed due to an internal error
     * @author Maximilian Rublik
     * @throws UserAlreadyExistingException 
     */
    private static boolean isValidUsername(String username) throws InternalErrorException, UserAlreadyExistingException {
		if (username.length() >= 30) {
        	// we restrict the username to be 30 char at max in the db
            return false;            
        }

        // check whether the username already exists in the database
        return UserRepository.isUniqueUsername(username);
    }

    /**
     * Checks received password for syntactical validity
     * @param password passed username
     * @return true if password is strong enough
     * @author Maximilian Rublik
     */
    private static boolean isValidPassword(String password) {
    	// length is at least 8 chars long
    	return password.length() > 7;    			
    }

    /**
     * Hashed passed password string using SHA-256
     * @param password string to hash
     * @return SHA-256 hash of password
     * @throws InternalErrorException hashing failed
     * @author Maximilian Rublik
     */
    private static String hashPassword (String password) throws InternalErrorException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return new String(hash);
        } catch (NoSuchAlgorithmException ex) {
            throw new InternalErrorException("there was a problem hashing the password", ex);
        }
    }

    /**
     * copied from <a href="https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-">stackoverflow</a>
     * since we don't want to implement our own check whether an email is correct, nor use some alien like regex
     * language. So we take what the java god gave us
     *
     * Part for length check is own
     *
     * @param email email address to check
     * @return boolean whether the email address is vaild in its format- and not longer than 100 chars
     */
    private static boolean isValidEmailAddress(String email) {
        if (email.length() >= 40) {
            // email longer than db says its possible
            return false;
        }

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
