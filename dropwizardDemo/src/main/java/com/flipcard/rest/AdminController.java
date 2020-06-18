package com.flipcard.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipcard.model.Course;
import com.flipcard.model.Professor;
import com.flipcard.model.Registration;
import com.flipcard.model.Student;
import com.flipcard.model.User;
import com.flipcard.service.AdminService;

@Path("/admin")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	private final AdminService adminService = new AdminService();

	// Retrieve list of all students
	@GET
	@Path("/allStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents() {
		return adminService.getStudents();
	}

	// Get a list of all courses in the university
	@GET
	@Path("/allCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewAllCourses() {
		return adminService.viewAllCourses();
	}

	// Fetches a list of registered students
	@GET
	@Path("/registeredStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Registration> getRegisteredStudents() {
		return adminService.viewRegisteredStudents();
	}

	// Retrieve list of all professors
	@GET
	@Path("/allProfessors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getAllProfessors() {
		return adminService.getProfessors();
	}

	// Create a new student
	@POST
	@Path("/postStudent")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertStudent(Student student) {
		int userId = student.getId();
		String username = student.getUsername();
		String password = student.getPassword();
		int roleId = student.getRoleId();
		User user = new User(userId, username, password, roleId);
		adminService.createUser(user);
		adminService.createStudent(student);
		LOGGER.info("Hit post service");
		LOGGER.info("Value of id from UI " + student.getId());
		LOGGER.info("Value of name from UI " + student.getName());
		String result = "Track saved : " + student;
		return Response.status(201).entity(result).build();
	}

	// Create a new professor
	@POST
	@Path("/postProfessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertProfessor(Professor professor) {
		int userId = professor.getId();
		String username = professor.getUsername();
		String password = professor.getPassword();
		int roleId = professor.getRoleId();
		User user = new User(userId, username, password, roleId);
		adminService.createUser(user);
		adminService.createProfessor(professor);
		LOGGER.info("Hit post service");
		LOGGER.info("Value of id from UI " + professor.getId());
		LOGGER.info("Value of name from UI " + professor.getName());
		String result = "Track saved : " + professor;
		return Response.status(201).entity(result).build();
	}
	
	//Create a new course
	@POST
	@Path("/postCourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertCourse(Course course){
		LOGGER.info(course.getCourseId() + " " + course.getCourseName() + " " + course.getCredits());
		adminService.addCourseToCatalog(course);
		String result = "Course with id " + course.getCourseId() + " added succesfully.";
		return Response.status(201).entity(result).build();
	}

	// Update professor details
	@PUT
	@Path("/updateProfessor/{professorId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorId") int professorId, Professor newProfessor)
			throws URIReferenceException {
		if (adminService.updateProfessor(professorId, newProfessor)) {
			LOGGER.info("Student updated");
		}
		return newProfessor;
	}

	// Update student details
	@PUT
	@Path("/updateStudent/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId") int studentId, Student student) throws URIReferenceException {
		if (adminService.updateStudent(studentId, student)) {
			LOGGER.info("Student updated");
		}
		return student;
	}

	// Delete a student
	@DELETE
	@Path("/deleteStudent/{studentId}")
	public Response deleteStudent(@PathParam("studentId") int studentId) throws URIReferenceException {
		adminService.deleteUser(studentId);
		adminService.deleteStudent(studentId);
		LOGGER.info("Student with id " + studentId + " deleted successfully");
		return Response.status(200).entity("Track Id " + studentId + " successfully deleted").build();
	}

	// Delete a professor
	@DELETE
	@Path("/deleteProfessor/{professorId}")
	public Response deleteProfessor(@PathParam("professorId") int professorId) throws URIReferenceException {
		adminService.deleteUser(professorId);
		adminService.deleteProfessor(professorId);
		LOGGER.info("Student with id " + professorId + " deleted successfully");
		return Response.status(200).entity("Track Id " + professorId + " successfully deleted").build();
	}

}
