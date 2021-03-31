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
public class ArticoloCreate {
    
    @NotEmpty
    public String titlo;
    @NotEmpty
    public String articolo;
    
}
