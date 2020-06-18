package com.flipcard.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBUtil {

    //private static Connection connection = null;
    private static Logger logger = Logger.getLogger(DBUtil.class);
    /**
     * @return connection to the DB
     */
    public static Connection getConnection() {
        try {
            java.lang.String driver = "com.mysql.cj.jdbc.Driver";
            java.lang.String url = "jdbc:mysql://localhost:3306/flipkartDB";
            java.lang.String user = "root";
            java.lang.String password = "Mehar123!";
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}



