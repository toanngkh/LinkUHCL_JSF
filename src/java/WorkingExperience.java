/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author toan
 */
public class WorkingExperience {
    
    private String company; // required
    private String title;   // required
    private String location;
    private String industry;
    private LocalDate startDay;  // required
    private LocalDate endDay;    // required if not currently working
    private String description;
    private boolean currentlyWorking;   // if false then endDay is required

    
    public WorkingExperience(String company, String title, String location, String industry, LocalDate startDay, LocalDate endDay, boolean currentlyWorking, String description){
        this.company = company;
        this.title = title;
        this.location = location;
        this.industry = industry;
        this.endDay = endDay;
        this.startDay = startDay;
        this.currentlyWorking = currentlyWorking;
        this.description = description;
    }
    
    public String toString(){
        String endDayFormat = "";
        String startDayFormat = startDay.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        Period period;
        if(endDay == null){
            endDayFormat = "present";
            period = startDay.until(LocalDate.now());
        }
        else{
            endDayFormat = endDay.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
            period = startDay.until(endDay);
        }
        String experience = "";
        experience += title + "<br />";
        experience += company + "<br />";
        experience += location + "<br />";
        experience += industry + "<br />";
        experience += startDayFormat + " - " + endDayFormat + " (" + (period.getYears() * 12 + period.getMonths()) + " months)<br />";
        experience += description + "<br />";
        return experience;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    
}
