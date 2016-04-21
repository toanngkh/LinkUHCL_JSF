/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toan
 */
public class Education {
    
    //private int eduNum;
    private String schoolName;
    private String degree;
    private int startYear;
    private int endYear;
    private String fieldOfStudy;
    private float gpa;
    private double grade;
    private String activities;
    private String description;
    
    public Education(String schoolName, String degree, int startYear, int endYear, String fieldOfStudy, float gpa, String activities, String description){
        this.schoolName = schoolName;
        this.degree = degree;
        this.startYear = startYear;
        this.endYear = endYear;
        this.fieldOfStudy = fieldOfStudy;
        this.gpa = gpa;
        this.activities = activities;
        this.description = description;
    }
    
    public String toString(){
        String education = "";
        education += "School: " + schoolName + "<br />";
        education += degree + ", " + fieldOfStudy + "<br />";
        education += startYear + " - " + endYear + "<br />";
        education += "GPA: " + gpa + "<br />";
        if(activities.isEmpty())
            education += "";
        else
            education += activities + "<br />";
        if(description.isEmpty())
            education += "";
        else
            education += description + "<br />";
        return education;
    }

    public float getGpa() {
        return gpa;
    }
    
    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
