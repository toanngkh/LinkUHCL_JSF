
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
 

/**
 *
 * @author toan
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    
    private String id;
    private String password;
    private Account loginAccount;
    
    final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/nguyent2758";
    Connection conn = null;  
    Statement state = null;    
    ResultSet rs = null; 
    
    public String Login(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "connectionerror";
        }
        
        try{
            conn = DriverManager.getConnection(DATABASE_URL, "nguyent2758", "kh4nht04n");
            state = conn.createStatement();
            rs = state.executeQuery("select * from linkuhcl_account where "
                                  + "account_id='" + id + "'");
            if(rs.next()){
                if(password.equals(rs.getString("password"))){
                    loginAccount = new Account(id, password);
                    loginAccount.setAccHolderName(rs.getString("name"));
                    loginAccount.setPassword(password);
                    loginAccount.setCity(rs.getString("city"));
                    loginAccount.setCountry(rs.getString("country"));
                    loginAccount.setDob(rs.getDate("dob").toLocalDate());
                    loginAccount.setEmail(rs.getString("email"));
                    loginAccount.setGender(rs.getString("gender"));
                    
                    // get all education information
                    rs = state.executeQuery("select * from linkuhcl_education "
                                          + "where account_id='" + id + "' "
                                          + "order by end_year desc");
                    while(rs.next()){
                        loginAccount.setEduList(rs.getString("school_name"), rs.getString("degree"), rs.getInt("start_year"), 
                                rs.getInt("end_year"), rs.getString("field_of_study"), rs.getFloat("grade"), 
                                rs.getString("activities"), rs.getString("description"));
                   }
                    
                    // get all skill informaiton
                    rs = state.executeQuery("select * from linkuhcl_skill "
                                          + "where account_id='" + id + "' "
                                          + "order by endorse desc");
                    while(rs.next()){
                        loginAccount.setSkillList(rs.getString("skill_name"), rs.getInt("endorse"));
                    }
                    
                    // get all working experience info
                    rs = state.executeQuery("select * from linkuhcl_workingexperience "
                                          + "where account_id='" + id + "' "
                                          + "order by end_day is null desc");
                    while(rs.next()){
                        if(rs.getDate("end_day") == null){
                            loginAccount.setExpList(rs.getString("company"), rs.getString("title"), 
                                rs.getString("location"), rs.getString("industry"), rs.getDate("start_day").toLocalDate(), 
                                null, rs.getBoolean("cur_working"), rs.getString("description"));
                        }
                        else{
                            loginAccount.setExpList(rs.getString("company"), rs.getString("title"), 
                                rs.getString("location"), rs.getString("industry"), rs.getDate("start_day").toLocalDate(), 
                                rs.getDate("end_day").toLocalDate(), rs.getBoolean("cur_working"), rs.getString("description"));
                        }
                    }
                    
                    return "home";
                }
                else
                    return "wrongpassword";
            }
            else 
                return "noid";
            
        }   
        catch(SQLException e){
            e.printStackTrace();
            return "connectionerror";
        }
        finally{
            try
            {
                rs.close();
                state.close();
                conn.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();    
            }
        }
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(Account loginAccount) {
        this.loginAccount = loginAccount;
    }
   
}
