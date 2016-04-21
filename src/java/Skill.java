/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toan
 */
public class Skill {
    
    private String skillName;
    private int endorseCount;
    
    public Skill(String skillName, int endorseCount){
        this.skillName = skillName;
        this.endorseCount = endorseCount;
    }
    
    public String toString(){
        String skill = "";
        skill += endorseCount + " " + skillName + "<br />";
        return skill;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getEndorseCount() {
        return endorseCount;
    }

    public void setEndorseCount(int endorseCount) {
        this.endorseCount = endorseCount;
    }
    
    public void endorse(){
        endorseCount++;
    }
    
}
