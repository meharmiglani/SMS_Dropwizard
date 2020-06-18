package com.flipcard.service;

import java.util.List;

import com.flipcard.dao.CourseCatalogDao;
import com.flipcard.dao.ProfessorDao;
import com.flipcard.dao.StudentDao;
import com.flipcard.model.Course;
import com.flipcard.model.Professor;
import com.flipcard.model.Registration;
import com.flipcard.model.Student;
import com.flipcard.model.User;

public class AdminService {
	private static StudentDao studentDao = new StudentDao();
	private static ProfessorDao professorDao = new ProfessorDao();
	private static CourseCatalogDao courseCatalogDao = new CourseCatalogDao();
	
	public List<Student> getStudents() {
		return studentDao.viewAllStudents();
	}
	
	public void createStudent(Student student) {
		studentDao.insertStudent(student);
	}

	public void createUser(User user) {
		studentDao.createUser(user);
	}

	public boolean deleteUser(int userId) {
		return studentDao.deleteUser(userId);
	}

	public boolean deleteStudent(int studentId) {
		return studentDao.deleteStudent(studentId);
	}
	
	public boolean updateStudent(int studentId, Student student) {
		return studentDao.updateStudent(studentId, student);
	}
	
	public List<Professor> getProfessors() {
		return professorDao.viewAllProfessors();
	}
	
	public boolean updateProfessor(int professorId, Professor newProfessor){
		return professorDao.updateProfessor(professorId, newProfessor);
	}
	
	public boolean deleteProfessor(int professorId) {
		return professorDao.deleteProfessor(professorId);
	}
	
	public boolean addCourseToCatalog(Course course){
		return courseCatalogDao.addCourseToCatalog(course);
	}
	
	public void createProfessor(Professor professor) {
		professorDao.insertProfessor(professor);
	}
	
	public List<Course> viewAllCourses(){
		return courseCatalogDao.viewAllCourses();
	}
	
	public List<Registration> viewRegisteredStudents(){
		return studentDao.getRegisteredStudents();
	}
}
