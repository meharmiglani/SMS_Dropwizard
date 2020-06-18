package com.flipcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipcard.constant.SQLConstantQueries;
import com.flipcard.utils.DBUtil;

public class UserDao{
	
	private final static Logger logger = Logger.getLogger(UserDao.class);
	
	public int checkIdentity(String username, String password) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(SQLConstantQueries.CHECK_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }
            return -1;
        }catch (SQLException e){
            logger.error(e.getMessage());
            return -1;
        }
	}
}
