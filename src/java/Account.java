/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.*;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author toan
 */
@ManagedBean
@SessionScoped
public class Account {
    // Accoutn object
    private String accountID;
    private String password;
    private String accHolderName;
    private String gender;
    private LocalDate dob;
    private String country;
    private String city;
    private String email;
    private String accountType;
    private ArrayList<WorkingExperience> expList = new ArrayList<>();
    private ArrayList<Education> eduList = new ArrayList<>();
    private ArrayList<Skill> skillList = new ArrayList<>();
    
    Connection conn = null;
    Statement state = null;
    ResultSet rs = null;
    final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/nguyent2758";
        
    // <editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String name) {
        this.accHolderName = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
     public ArrayList<WorkingExperience> getExpList() {
        return expList;
    }

    public void setExpList(String company, String title, String location, String industry, LocalDate startDay, LocalDate endDay, boolean currentlyWorking, String description) {
        this.expList.add(new WorkingExperience(company, title, location, industry, startDay, endDay, currentlyWorking, description));
    }

    public ArrayList<Education> getEduList() {
        return eduList;
    }

    public void setEduList(String schoolName, String degree, int startYear, int endYear, String fieldOfStudy, float gpa, String activities, String description) {
        this.eduList.add(new Education(schoolName, degree, startYear, endYear, fieldOfStudy, gpa, activities, description));
    }

    public ArrayList<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(String skillName, int endorse) {
        this.skillList.add(new Skill(skillName, endorse));
    }
    // </editor-fold>
    
    public Account(String id, String pass){
        accountID = id;
        password = pass;
    }
    public Account(String id, String pass, String name, String gend, LocalDate dob, String country, String city, String email, String type){
        accountID = id;
        password = pass;
        accHolderName = name;
        gender = gend;
        this.dob = dob;
        this.country = country;
        this.city = city;
        this.email = email;
        accountType = type;
    }
    
    public void DisplayProfile(String id){
        try{
            conn = DriverManager.getConnection(DATABASE_URL, "nguyent2758", "kh4nht04n");
            state = conn.createStatement();
            rs = state.executeQuery("SELECT account_id, name, gender "
                                  + "FROM linkuhcl_account "
                                  + "WHERE account_id = '" + id + "'");
            if(rs.next()){
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("");
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                conn.close();
                state.close();
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    public void DisplayAllFirstConnections(String id){
       try{
            conn = DriverManager.getConnection(DATABASE_URL, "nguyent2758", "kh4nht04n");
            state = conn.createStatement();
            System.out.println("First connections: ");
            rs = state.executeQuery("SELECT name, account_id, gender, company "
                                  + "FROM linkuhcl_account INNER JOIN linkuhcl_connection "
                                  + "ON account_id = sender_id OR account_id = receiver_id "
                                  + "WHERE (sender_id = '" + id + "' OR receiver_id = '" + id + "') AND "
                                        + "account_id <> '" + id + "'"
                                  + "ORDER BY name");
            if(!rs.next()){
                System.out.println("You don't have any connection");
                System.out.println("");
            }
            else{
                do {
                    System.out.println(rs.getString(1) + " with account ID: " + rs.getString(2));
                }
                while(rs.next());
                System.out.println("");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                conn.close();
                state.close();
                rs.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public String DisplayProfession(){
        StringBuilder prof = new StringBuilder();
        // get the major with most recent year
        int thisYear = LocalDate.now().getYear();
        for(Education edu : eduList){
            if(thisYear >= edu.getStartYear() && thisYear <= edu.getEndYear())
                prof.append(edu.getDegree() + ", " + edu.getFieldOfStudy() + "\n");
        }
        return prof.toString();
    }
    
    public String DisplayIndustry(){
        String industry = "";
        for (WorkingExperience exp : expList) {
            if(exp.isCurrentlyWorking())
                industry = exp.getIndustry();
        }
        return industry;
    }
    
    public String DisplayRecentEmployer(){
        String empl = "";
        ArrayList<LocalDate> days = new ArrayList<>();
        boolean isWorking = false;
        for (WorkingExperience exp : expList) {
            if(exp.isCurrentlyWorking()){
                empl = exp.getCompany();
                isWorking = true;
                break;
            }
            else
                days.add(exp.getEndDay());
        }
        if(!isWorking){
            LocalDate recentDay = Collections.max(days);
            for (WorkingExperience exp : expList) {
                if(exp.getEndDay().equals(recentDay)){
                    empl = exp.getCompany();
                    break;
                }
            }
        }
        return empl;
    }
    
    public String DisplayRecentEducation(){
        String edu = "";
        ArrayList<Integer> endYears = new ArrayList<>();
        boolean isInSchool = false;
        int thisYear = LocalDate.now().getYear();
        for(Education ed : eduList){
            if(thisYear >= ed.getStartYear() && thisYear <= ed.getEndYear()){
                edu = ed.getSchoolName();
                isInSchool = true;
                break;
            }
            else{
                endYears.add(ed.getEndYear());
            }
        }
        if(!isInSchool){
            int recentYear = Collections.max(endYears).intValue();
            for(Education ed : eduList){
                if(ed.getEndYear() == recentYear)
                    edu = ed.getSchoolName();
            }
        }
        return edu;
    }
        
    public void DeleteEducationRow(Education edu){
        eduList.remove(edu);
    }
    
    public void DeleteExperienceRow(WorkingExperience exp){
        expList.remove(exp);
    }
    
    public void DeleteSkillRow(Skill skl){
        skillList.remove(skl);
    }
    
    public String CalculateNextID(String id){
        String result = "j";
        int num = Integer.parseInt(id.substring(1));
        ++num;
        result += num;
        return result;
    }
    
}
