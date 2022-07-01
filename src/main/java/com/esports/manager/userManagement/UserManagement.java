package com.esports.manager.userManagement;

import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.newsfeed.NewsfeedLogic;
import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.userManagement.beans.UserSessionBean;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.*;
import com.esports.manager.userManagement.servlets.UserSearchServlet;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * collection of methods that will be used in user management
 *
 * @author Maximilian Rublik, Philipp Phan
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
     * @author Maximilian Rublik
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

    /**
     * Fetches user entity by his username
     * @param username username of requested user
     * @return user entity (if username is known to system)
     * @throws NoSuchUserException no user with username found in database
     * @throws InternalErrorException sql error; database error; runtime error; (developer fault)
     * @author Philipp Phan
     */
    public static User fetchUserByUsername(final String username) throws NoSuchUserException, InternalErrorException {
        log.debug("fetching user by username...");
        return UserRepository.getByUsername(username);
    }

    /**
     * Checks if username and password (from html form) align with those in database
     *
     * @param username passed username by unauthenticated user
     * @param password passed password by unauthenticated user.
     * @param session  session in which the user will be logged in if username and password are correct
     * @throws InternalErrorException an unexpected and fatal internal error occurred
     * @throws NoSuchUserException    a user with passed username was not found in database
     * @author Philipp Phan
     */
    public static void performLogin(String username, String password, HttpSession session)
            throws InternalErrorException, NoSuchUserException, WrongCredentialsException {
        try {
            User user = UserRepository.getByUsername(username);
            if (username.equals(user.getUsername()) && hashPassword(password).equals(user.getPasswordHash())) {
                // Create sessionBean
                UserSessionBean userSessionBean = new UserSessionBean();

                // Insert user object inside sessionBean
                userSessionBean.setUser(UserRepository.getByUsername(username));

                // Insert sessionBean in HttpSession
                session.setAttribute("userSessionBean", userSessionBean);
                log.info("Login successful. Welcome");
            } else {
                log.warn("login not successful");
                throw new WrongCredentialsException();
            }
        } catch (InternalErrorException e) {
            log.error("Internal Error occurred while login: " + e.getMessage(), e);
            throw e;
        } catch (NoSuchUserException e) {
            log.warn("cannot perform login because there is no user with passed username");
            throw e;
        }
    }


    /**
     * Perform registration with passed user data after it has been checked and verified.
     *
     * @param username requested username for registration (must be available)
     * @param password requested password for registration (must meet certain criteria)
     * @param email    requested email for registration (must be unique in database)
     * @throws InvalidInputException         the passed input is syntactically invalid or not allowed
     * @throws InternalErrorException        an unexpected internal error occurred
     * @throws UsernameAlreadyTakenException the requested username is not available
     * @author Maximilian Rublik
     */
    public static void registerUser(String username, String password, String email, Boolean isAdmin) throws InvalidInputException, InternalErrorException, UsernameAlreadyTakenException {
        if (checkPassedUserData(username, password, email)) {

            // check if username is available
            if (!UserManagement.isUsernameAvailable(username)) {
                throw new UsernameAlreadyTakenException();
            }

            // create and persist new user
            User newUser = new User(username, email, hashPassword(password), false);
            UserRepository.createNewUser(newUser);

            // add happy news of user registration to newsfeed
            NewsfeedLogic.registerUserRegistration(newUser);
        } else {
            throw new InvalidInputException("registerUser: Invalid Input");
        }
    }

    /**
     * Checks received user data for syntactical validity
     *
     * @param username passed username
     * @param password passed password
     * @param email    passed email address
     * @return true, if all user data is valid
     * @author Maximilian Rublik
     */
    private static boolean checkPassedUserData(String username, String password, String email) {
        return isValidUsername(username) && isValidPassword(password) && isValidEmailAddress(email);
    }

    /**
     * Checks received username for syntactical validity and uniqueness
     *
     * @param username passed username
     * @return true if username is valid and allowed
     * @author Maximilian Rublik
     */
    private static boolean isValidUsername(String username) {
        if (username == null || username.isBlank()) return false;
        if(!username.matches("^[a-zA-Z_\\d]*$")) {
            return false;
        }
        // we restrict the username to be 30 char at max in the db
        return username.length() < 30;
    }

    /**
     * Checks received password for syntactical validity
     *
     * @param password passed username
     * @return true if password is strong enough
     * @author Maximilian Rublik
     */
    private static boolean isValidPassword(String password) {
        if (password == null || password.isBlank()) return false;
        // length is at least 8 chars long
        return password.length() > 7;
    }

    /**
     * Hashed passed password string using SHA-256
     *
     * @param password string to hash
     * @return SHA-256 hash of password
     * @throws InternalErrorException hashing failed
     * @author Maximilian Rublik
     */
    public static String hashPassword(String password) throws InternalErrorException {
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
     * <p>
     * Part for length check is own
     *
     * @param email email address to check
     * @return boolean whether the email address is vaild in its format- and not longer than 100 chars
     * @author Maximilian Rublik
     */
    private static boolean isValidEmailAddress(String email) {
        if (email == null || email.isBlank()) return false;

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

    /**
     * Checks if there is a logged in user in session
     *
     * @param session http session
     * @return logged in user
     * @throws UnauthorizedException there is no authorized user stored in session
     * @author Maxmilian Rublik
     */
    public static User getAuthorizedUser(final HttpSession session) throws UnauthorizedException, InternalErrorException {
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");
        if (userSessionBean == null || userSessionBean.getUser() == null)
            throw new UnauthorizedException();
        User user = userSessionBean.getUser();
        // check if user still exists
        try {
            user = UserRepository.getByUsername(user.getUsername());
        } catch (NoSuchUserException e) {
            log.warn("user was in session but did not exist in database");
            throw new UnauthorizedException();
        }

        return user;
    }

    /**
     * Returns all users in database
     *
     * @return list of all users
     * @throws InternalErrorException a database error occurred
     * @author Philipp Phan
     */
    public static List<User> fetchAllUsers() throws InternalErrorException {
        log.debug("fetching all users...");
        List<User> foundUsers = UserRepository.fetchAllUserWithUsernamePattern(".*");
        log.info("fetched all users from database");
        return foundUsers;
    }

    /**
     * Returns all users in database which usernames match the given pattern
     *
     * @param pattern regex pattern
     * @return list of users which username matches regex pattern
     * @throws InternalErrorException a database error occurred
     * @author Philipp Phan
     */
    public static List<User> fetchUserByUsernamePattern(final String pattern) throws InternalErrorException {
        log.debug(String.format("fetching users matching pattern '%s'...", pattern));
        return UserRepository.fetchAllUserWithUsernamePattern(pattern);
    }

    /**
     *
     * @param teamId Unique id of the team
     * @return list of users
     * @throws InternalErrorException a database error occurred
     * @author Maximilian Rublik
     * */
    public static List<User> fetchUserNotAlreadyMember(Long teamId) throws InternalErrorException {
        log.debug("fetching users not already members in team..");

        List<User> users = fetchAllUsers();
        List<Member> members = TeamManagement.fetchMembersByTeamId(teamId);
        List<User> usersToRemove = new LinkedList<>();
        for (User user : users) {
            for (Member member : members) {
                if (user.getUsername().equals(member.getUsername()) && member.getTeamId() == teamId) {
                    usersToRemove.add(user);
                }
            }
        }
        users.removeAll(usersToRemove);
        return users;
    }


    /**
     * Deletes User from database
     *
     * @param username unique name of the user
     * @throws InternalErrorException an internal Error occured while removing the user
     * @author Philipp Phan
     */
    public static void removeUser(String username) {
        try {
            UserRepository.deleteUser(username);
            log.info("User has been deleted from database");
        }catch (InternalErrorException e){
            log.error("Internal Error occured" + e.getMessage());
        }
    }

    /**
     * Gets ID of teams in which the user is a member
     *
     * @param user user object
     * @throws InternalErrorException data base error
     * @author Philipp Phan
    */
    public static List<Long> fetchTeamsIdsOfUser(final User user) throws InternalErrorException {
        return UserRepository.fetchTeamsOfUser(user);
    }
}
