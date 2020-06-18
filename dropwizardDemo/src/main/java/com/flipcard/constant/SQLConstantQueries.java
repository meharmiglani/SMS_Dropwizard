package com.flipcard.constant;

public class SQLConstantQueries {
	public static final String CHECK_USER = "SELECT userId FROM User WHERE username = ? AND password = ?";
    public static final String GET_ROLE = "SELECT roleId FROM User WHERE username = ? AND password = ?";
    public static final String GET_ROLE_BY_ID = "SELECT roleId FROM User WHERE userId = ?";
    public static final String GET_STUDENT_NAME = "SELECT name FROM Student WHERE studentId = ?";
    public static final String GET_CATALOG = "SELECT courseId, name, credits FROM Course WHERE professorId > 0 AND catalogId = ? ORDER BY courseId ASC";
    public static final String CHECK_COURSE_EXISTENCE = "SELECT name FROM Course WHERE courseId = ?";
    public static final String COUNT_OF_STUDENTS = "SELECT countOfStudents FROM Course WHERE courseId = ?";
    public static final String UPDATE_COUNT = "UPDATE Course SET countOfStudents = ? WHERE courseId = ?";
    public static final String CHECK_COURSE_LIMIT = "SELECT COUNT(courseId) from RegisterCourse WHERE studentId = ?";
    public static final String ADD_STUDENT_COURSE = "INSERT INTO RegisterCourse(courseId, studentId, courseName, studentName) VALUES(?, ?, ?, ?)";
    public static final String UPDATE_MARKS = "UPDATE RegisterCourse SET marks = ? WHERE studentId = ? AND courseId = ?";
    public static final String CHECK_ALREADY_ADDED_COURSE = "SELECT COUNT(*) FROM RegisterCourse WHERE studentId = ? AND courseId = ?";
    public static final String DELETE_STUDENT_COURSE = "DELETE FROM RegisterCourse WHERE studentId = ? AND courseId = ?";
    public static final String VIEW_STUDENT_LIST =  "SELECT RegisterCourse.studentName, RegisterCourse.studentId, RegisterCourse.courseName, RegisterCourse.marks " +
                                                    "FROM ProfessorByCourse INNER JOIN RegisterCourse ON ProfessorByCourse.courseId = RegisterCourse.courseId " +
                                                    "WHERE ProfessorByCourse.professorId = ?";
    public static final String REGISTERED_COURSES = "SELECT courseId, courseName FROM RegisterCourse WHERE studentId = ?";
    public static final String VIEW_STUDENT_LIST_BY_COURSE_ID = "SELECT DISTINCT RegisterCourse.studentName, RegisterCourse.studentId, RegisterCourse.courseName, RegisterCourse.marks " +
                                                                "FROM ProfessorByCourse INNER JOIN RegisterCourse ON ProfessorByCourse.courseId = RegisterCourse.courseId " +
                                                                "WHERE ProfessorByCourse.professorId = ? AND RegisterCourse.courseId = ?";
    public static final String VIEW_AVAILABLE_COURSE_LIST_TO_TEACH = "SELECT courseId, name FROM Course WHERE professorId = ?";
    public static final String SELECT_COURSE_TO_TEACH_PROFESSOR = "UPDATE Course SET professorId = ? WHERE courseId = ? AND professorId = ?";
    public static final String SELECT_COURSE_TO_TEACH_PROFESSOR_2 = "INSERT INTO ProfessorByCourse VALUES(?, ?)";
    public static final String VIEW_COURSES_TAUGHT = "SELECT courseId, name, countOfStudents FROM Course WHERE professorId = ?";
    public static final String CHECK_FOR_REPORT_CARD = "SELECT COUNT(*) FROM RegisterCourse WHERE marks > -1 AND studentId = ?";
    public static final String GET_REPORT_CARD = "SELECT courseId, courseName, marks FROM RegisterCourse WHERE studentId = ?";
    public static final String GET_FINAL_MARKS = "SELECT AVG(marks) FROM RegisterCourse WHERE studentId = ? ";
    public static final String IS_REGISTERED = "SELECT isRegistered FROM Student WHERE studentId = ?";
    public static final String REGISTER_STUDENT = "UPDATE Student SET isRegistered = ? WHERE studentId = ?";
    public static final String FETCH_COURSE_FEE = "SELECT Course.courseId, Course.name, Course.fee FROM Course INNER JOIN RegisterCourse ON Course.courseId = RegisterCourse.courseId WHERE RegisterCourse.studentId = ?";
    public static final String GET_SCHOLARSHIP_AMOUNT = "SELECT scholarship FROM Student WHERE studentId = ?";
//    public static final String INSERT_PAYMENT = "INSERT INTO Payment VALUES(?,?,?)";
//    public static final String GET_PAYMENT_ID = "SELECT paymentId FROM Payment WHERE studentId = ?";
    public static final String INSERT_STUDENT_REGISTRATION = "INSERT INTO StudentRegistration VALUES(?,?,?,?,?)";
    public static final String CREATE_USER = "INSERT INTO User VALUES(?,?,?,?)";
    public static final String CREATE_STUDENT = "INSERT INTO Student VALUES(?,?,?,?,?,?)";
    public static final String CREATE_PROFESSOR = "INSERT INTO Professor VALUES(?,?,?)";
    public static final String CREATE_ADMIN = "INSERT INTO Admin VALUES(?,?,?)";
    public static final String CREATE_COURSE = "INSERT INTO Course VALUES(?,?,?,?,?,?,?)";
    public static final String DELETE_COURSE = "DELETE FROM Course WHERE courseId = ?";
    public static final String DELETE_REGISTER_COURSE = "DELETE FROM RegisterCourse WHERE courseId = ?";
    public static final String DELETE_PROFESSOR_COURSE = "DELETE FROM ProfessorByCourse WHERE courseId = ?";
    public static final String GET_ALL_STUDENTS = "SELECT User.userId, User.username, Student.name, Student.email, Student.gender, Student.isRegistered, Student.scholarship " +
                                                  "FROM User INNER JOIN Student ON User.userId = Student.studentId ORDER BY User.userId ASC";
    public static final String GET_ALL_PROFESSORS = "SELECT User.userId, User.username, Professor.name, Professor.email FROM User INNER JOIN Professor ON User.userId = Professor.professorId";
    public static final String GET_PAYMENT_DETAILS = "SELECT name, Student.studentId, registrationId, mode, dateTime, amount FROM Payment INNER JOIN StudentRegistration ON Payment.paymentId = StudentRegistration.paymentId INNER JOIN Student ON Student.studentId = StudentRegistration.studentId";
    public static final String DELETE_USER_TABLE = "DELETE FROM User WHERE userId = ?";
    public static final String DELETE_PROFESSOR_PROFESSOR_TABLE = "DELETE FROM Professor WHERE professorId = ?";
    public static final String DELETE_PROFESSOR_COURSE_TABLE = "DELETE FROM ProfessorByCourse WHERE professorId = ?";
    public static final String UPDATE_COURSE_TABLE = "UPDATE Course SET professorId = ? WHERE professorId = ?";
    public static final String DELETE_STUDENT_TABLE = "DELETE FROM Student WHERE studentId = ?";
    public static final String DELETE_STUDENT_REGISTRATION = "DELETE FROM StudentRegistration WHERE studentId = ?";
    public static final String GET_REGISTERED_COURSE_BY_ID = "SELECT courseId FROM RegisterCourse WHERE studentId = ?";
    public static final String DELETE_REGISTERED_COURSES = "DELETE FROM RegisterCourse WHERE studentId = ?";
    public static final String EDIT_USER = "UPDATE User SET username = ?, password = ? WHERE userId = ?";
    public static final String UPDATE_STUDENT = "UPDATE Student SET name = ?, email = ?, gender = ?, scholarship = ? WHERE studentId = ?";
    public static final String UPDATE_PROFESSOR = "UPDATE Professor SET name = ?, email = ? WHERE professorId = ?";
    public static final String VIEW_ALL_COURSES = "SELECT courseId, name, professorId, countOfStudents, credits, fee, description FROM Course INNER JOIN Catalog ON Course.catalogId = Catalog.catalogId";
}
