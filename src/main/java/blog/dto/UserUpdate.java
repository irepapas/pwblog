/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dto;

/**
 *
 * @author Irene
 */
public class UserUpdate {
    
    public String fName;
    public String lName;
    public String pwd;
    public String email;
    
    public UserUpdate() {
    }
    
    public UserUpdate(String fname, String lname, String pwd, String email) {
        this.fName = fname;
        this.lName = lname;
        this.pwd = pwd;
        this.email = email;
   
    }
}
