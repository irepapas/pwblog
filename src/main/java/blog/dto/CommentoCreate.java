/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dto;

import blog.entity.Articolo;
import blog.entity.User;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Irene
 */
public class CommentoCreate {
    
    @NotEmpty
    public String textComment;
    @NotEmpty
    public Articolo articoloId;
    @NotEmpty
    public User userId;
    
}
