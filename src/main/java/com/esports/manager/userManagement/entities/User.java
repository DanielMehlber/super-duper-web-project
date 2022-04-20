package com.esports.manager.userManagement.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

/**
 * The User entity contains all user data of a specific user.
 */
public class User {

    @ResultSetMapping("username")
    private String username;

    @ResultSetMapping("passwordHash")
    private String passwordHash;

    private User() {}
}
