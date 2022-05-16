package com.esports.manager.util;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceCreator {

    public static DataSource createNewDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db");
        ds.setUsername("mysql-user");
        ds.setPassword("Password123!?");

        return ds;
    }

}
