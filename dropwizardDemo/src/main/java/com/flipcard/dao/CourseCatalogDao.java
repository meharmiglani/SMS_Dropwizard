package com.flipcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.constant.SQLConstantQueries;
import com.flipcard.model.Course;
import com.flipcard.utils.DBUtil;

//Performs all operations on Course Catalog (CRUD)
public class CourseCatalogDao{
    private final static Logger logger = Logger.getLogger(CourseCatalogDao.class);

    //Adds a course to the catalog
    public boolean addCourseToCatalog(Course course) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(SQLConstantQueries.CREATE_COURSE);
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setInt(3, 0);
            statement.setInt(4, 0);
            statement.setInt(5, course.getCredits());
            statement.setDouble(6, course.getFee());
            statement.setInt(7, course.getCatalogId());
            int row = statement.executeUpdate();

            return row == 1;
        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }

    //Deletes a course from the catalog
    public boolean deleteCourse(int courseId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;

        try{
            statement = conn.prepareStatement(SQLConstantQueries.DELETE_COURSE);
            statement.setInt(1, courseId);
            int row = statement.executeUpdate();

            statement1 = conn.prepareStatement(SQLConstantQueries.DELETE_REGISTER_COURSE);
            statement1.setInt(1, courseId);
            int row1 = statement1.executeUpdate();

            statement2 = conn.prepareStatement(SQLConstantQueries.DELETE_PROFESSOR_COURSE);
            statement2.setInt(1, courseId);
            int row2 = statement2.executeUpdate();

            return row == 1 && row1 == 1 && row2 == 1;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }

    //Fetches a list of all available courses
    public List<Course> viewAllCourses() {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        List<Course> courseList = new ArrayList<>();
        try{
            statement = conn.prepareStatement(SQLConstantQueries.VIEW_ALL_COURSES);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Course course = new Course();
                course.setCourseId(resultSet.getInt(1));
                course.setCourseName(resultSet.getString(2));
                course.setProfessorId(resultSet.getInt(3));
                course.setCountOfStudents(resultSet.getInt(4));
                course.setCredits(resultSet.getInt(5));
                course.setFee(resultSet.getDouble(6));
                course.setCatalogType(resultSet.getString(7));
                courseList.add(course);
            }
            return courseList;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        }
    }
}
