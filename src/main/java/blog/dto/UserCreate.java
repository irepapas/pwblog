/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dto;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Irene
 */
public class UserCreate {
    
    public String fName;
    @NotEmpty
    public String lName;
    @NotEmpty
    public String email;
    @NotEmpty
    public String pwd;
    
}
