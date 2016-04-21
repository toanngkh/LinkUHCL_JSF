/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author toan
 */
@ManagedBean
@ViewScoped
public class PositionBean implements Serializable {
    
    private String company; // required
    private String title;   // required
    private String location;
    private String industry;
    private LocalDate startDay;  // required
    private LocalDate endDay;    // required if not currently working
    private String description;
    private boolean currentlyWorking;   // if false then endDay is required
    private ArrayList<WorkingExperience> posList = new ArrayList<>();

    
    public PositionBean(){
    }
    
    @ManagedProperty("#{LoginBean}")
    private LoginBean loginBean; 

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    public String AddNewPosition(){
        loginBean.getLoginAccount().setExpList(company, title, location, industry, startDay, endDay, currentlyWorking, description);
        return "profile";
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if(company.isEmpty())
            throw new IllegalArgumentException("Company name is required.");
        else
            this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.isEmpty())
            throw new IllegalArgumentException("Title is required.");
        else
            this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        if(startDay == null)
            throw new IllegalArgumentException("Starting day is required.");
        else
            this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        if(!currentlyWorking){
            if(endDay == null)
                throw new IllegalArgumentException("Ending day is required.");
            else
                this.endDay = endDay;
        }
        else{
            this.endDay = LocalDate.now();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCurrentlyWorking() {
        return currentlyWorking;
    }

    public void setCurrentlyWorking(boolean currentlyWorking) {
        this.currentlyWorking = currentlyWorking;
    }
    
}
