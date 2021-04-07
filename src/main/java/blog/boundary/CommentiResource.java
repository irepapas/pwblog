/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.boundary;

import blog.control.ArticoloStore;
import blog.control.CommentiStore;
import blog.entity.Commento;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Irene
 */
@RolesAllowed({"ADMIN", "USER"})
public class CommentiResource {


    @Inject
    private CommentiStore commentiStore;

    private Long commentId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Commento find() {
        Commento c= commentiStore.find(commentId).orElseThrow(() -> new NotFoundException());
        return c;
    }

    
    
     public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
}
