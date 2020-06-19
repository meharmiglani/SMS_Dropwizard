package com.flipcard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipcard.constant.SQLConstantQueries;
import com.flipcard.model.Course;
import com.flipcard.model.Registration;
import com.flipcard.model.Student;
import com.flipcard.model.User;
import com.flipcard.utils.DBUtil;

public class StudentDao{
	private static final Logger logger = LoggerFactory.getLogger(StudentDao.class);    
	private static final RegisterCourseDao registerCourseDao = new RegisterCourseDao();
	
	public List<Student> viewAllStudents() {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        List<Student> studentList = new ArrayList<>();

        try{
            statement = conn.prepareStatement(SQLConstantQueries.GET_ALL_STUDENTS);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt(1));
                student.setUsername(resultSet.getString(2));
                student.setName(resultSet.getString(3));
                student.setEmail(resultSet.getString(4));
                student.setGender(resultSet.getString(5));
                student.setRegistrationStatus(resultSet.getString(6));
                student.setScholarshipAmount(resultSet.getDouble(7));
                studentList.add(student);
            }
            return studentList;

        }catch (SQLException e){
        	logger.error(e.getMessage());
            return null;
        }
    }
	
	public List<Course> viewRegisteredCourses(int studentId) {
        List<Course> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;

        try{
            statement = conn.prepareStatement(SQLConstantQueries.REGISTERED_COURSES);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int courseId = resultSet.getInt(1);
                String courseName = resultSet.getString(2);
                Course course = new Course(courseId, courseName);
                list.add(course);
            }
            return list;

        }catch(SQLException e){
            logger.error(e.getMessage());
            return null;
        }
    }
	
	public boolean createUser(User user) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(SQLConstantQueries.CREATE_USER);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRoleId());
            int row1 = statement.executeUpdate();

            return row1 == 1;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }
	
	public boolean insertStudent(Student student) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;

        try{
            statement = conn.prepareStatement(SQLConstantQueries.CREATE_STUDENT);
            statement.setInt(1,student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getGender());
            statement.setString(5, "no");
            statement.setDouble(6, student.getScholarshipAmount());
            int row = statement.executeUpdate();
            return row == 1;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }
	
	public boolean deleteStudent(int studentId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(SQLConstantQueries.DELETE_STUDENT_TABLE);
            statement.setInt(1, studentId);
            int row = statement.executeUpdate();
            
            //deleteStudentRegistration(studentId);
            
            if(!updateCountsOfCourses(studentId)){
                //logger.error("Could not update count of students");
            }
            if(!deleteRegisteredCourses(studentId)){
                //logger.error("Could not delete registered courses of the student");
            }
            return row == 1;
        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }
	
	public boolean updateCountsOfCourses(int studentId){
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(SQLConstantQueries.GET_REGISTERED_COURSE_BY_ID);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int courseId = resultSet.getInt(1);
                int count = registerCourseDao.getCountOfStudents(courseId);
                registerCourseDao.updateStudentCount(courseId, count, 2);
            }
            return true;
        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }
	
	public boolean deleteRegisteredCourses(int studentId){
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(SQLConstantQueries.DELETE_REGISTERED_COURSES);
            statement.setInt(1, studentId);
            int row = statement.executeUpdate();
            return row == 1;
        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }
	
	public boolean deleteUser(int userId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(SQLConstantQueries.DELETE_USER_TABLE);
            statement.setInt(1, userId);
            int row1 = statement.executeUpdate();

            return row1 == 1;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }
	
	public boolean updateStudent(int studentId, Student student) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(SQLConstantQueries.UPDATE_STUDENT);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getGender());
            statement.setDouble(4, student.getScholarshipAmount());
            statement.setInt(5, studentId);
            int row = statement.executeUpdate();
            return row == 1;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        }
    }
	
	public String getStudentName(int studentId) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;

        try{
            statement = conn.prepareStatement(SQLConstantQueries.GET_STUDENT_NAME);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getString(1);
            }

            logger.error("No such student found");
            return "";

        }catch (SQLException e){
            logger.error(e.getMessage());
            return "";
        }
    }
	
	public List<Registration> getRegisteredStudents(){
        Connection conn = DBUtil.getConnection();
        PreparedStatement statement = null;
        List<Registration> registrationList = new ArrayList<>();
        try{
            statement = conn.prepareStatement(SQLConstantQueries.GET_PAYMENT_DETAILS);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Registration registration = new Registration();
                registration.setStudentName(resultSet.getString(1));
                registration.setStudentId(resultSet.getInt(2));
                registration.setRegistrationId(resultSet.getInt(3));
                registration.setMode(resultSet.getString(4));
                registration.setDate(resultSet.getObject(5, LocalDate.class));
                registration.setAmount(resultSet.getDouble(6));
                registrationList.add(registration);
            }
            return registrationList;

        }catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        }
    }
}
