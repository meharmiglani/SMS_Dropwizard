package com.flipcard.model;

public class Student extends User{
	private double scholarshipAmount;
    private String registrationStatus;
    private String gender;

    public Student(){

    }

    public Student(int studentId, String username, String name, String email, double scholarshipAmount, String gender){
        super(studentId, username, "", name, email, 3);
        this.gender = gender;
        this.scholarshipAmount = scholarshipAmount;
    }

    public double getScholarshipAmount() {
        return scholarshipAmount;
    }

    public String getGender() {
        return gender;
    }

    public void setScholarshipAmount(double scholarshipAmount) {
        this.scholarshipAmount = scholarshipAmount;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}

