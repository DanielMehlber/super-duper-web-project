package com.esports.manager.userManagement.servlets;

import com.esports.manager.global.db.queries.QueryHandler;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import javax.management.Query;
import javax.sql.DataSource;

public class ApplicationInitServlet extends HttpServlet {

    @Resource(lookup = "java:jboss/datasources/eSportsDS")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        /*
         * Unfortunately the container (=application server) only injects DataSources into registered servlets (like this one).
         * The DataSource is not needed in a servlet, but in the QueryHandler (where prepared statements are created).
         * This is why the injected datasource is then injected into the QueryHandler (passed-on, so to say).
         */
        QueryHandler.setDataSource(dataSource);
    }
}
