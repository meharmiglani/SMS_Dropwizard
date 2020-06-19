package com.flipcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipcard.constant.SQLConstantQueries;
import com.flipcard.model.Professor;
import com.flipcard.utils.DBUtil;

//Performs all CRUD operations on a professor
public class ProfessorDao{
	private static final Logger logger = LoggerFactory.getLogger(ProfessorDao.class);

    //Creates a new professor
    public boolean insertProfessor(Professor professor){
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;

        try{
            statement = conn.prepareStatement(SQLConstantQueries.CREATE_PROFESSOR);
            statement.setInt(1,professor.getId());
            statement.setString(2, professor.getName());
            statement.setString(3, professor.getEmail());
            int row = statement.executeUpdate();
            return row == 1;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }

    ////Deletes a professor from the DB (Has a cascading effect with the user table as well)
    public boolean deleteProfessor(int professorId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;

        try{
            statement = conn.prepareStatement(SQLConstantQueries.DELETE_PROFESSOR_PROFESSOR_TABLE);
            statement.setInt(1, professorId);
            int row2 = statement.executeUpdate();

            statement1 = conn.prepareStatement(SQLConstantQueries.DELETE_PROFESSOR_COURSE_TABLE);
            statement1.setInt(1, professorId);
            statement1.executeUpdate();

            statement2 = conn.prepareStatement(SQLConstantQueries.UPDATE_COURSE_TABLE);
            statement2.setInt(1, 0);
            statement2.setInt(2, professorId);
            statement2.executeUpdate();

            return row2 == 1;

        }catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    //Updates the details of a particular professor
    public boolean updateProfessor(int professorId, Professor newProfessor) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(SQLConstantQueries.UPDATE_PROFESSOR);
            statement.setString(1, newProfessor.getName());
            statement.setString(2, newProfessor.getEmail());
            statement.setInt(3, professorId);
            int row = statement.executeUpdate();
            return row == 1;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }

    //List the details of all existing professors in the university
    public List<Professor> viewAllProfessors() {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        List<Professor> professorList = new ArrayList<>();
        try{
            statement = conn.prepareStatement(SQLConstantQueries.GET_ALL_PROFESSORS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Professor professor = new Professor();
                professor.setId(resultSet.getInt(1));
                professor.setUsername(resultSet.getString(2));
                professor.setName(resultSet.getString(3));
                professor.setEmail(resultSet.getString(4));
                professorList.add(professor);
            }
            return professorList;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        }
    }
}