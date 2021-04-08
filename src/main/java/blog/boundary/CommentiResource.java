/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.boundary;


import blog.control.CommentiStore;
import blog.entity.Commento;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Irene
 */
@RolesAllowed({"ADMIN", "USER"})
public class CommentiResource {


    @Inject
    private CommentiStore commentiStore;

    private Long commentoId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Commento find() {
        Commento c= commentiStore.find(commentoId).orElseThrow(() -> new NotFoundException());
        return c;
    }
    @RolesAllowed({"ADMIN"})
    @PATCH
    public Response nascondiCommento() {
        commentiStore.find(commentoId).orElseThrow(() -> new NotFoundException());
        commentiStore.nascosto(commentoId);
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancellaCommento(){
        Commento commento = (Commento) commentiStore.cercaRefComm(commentoId);
        commento.setVisibile(true);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    
   
     public Long getCommentId() {
        return commentoId;
    }

    public void setCommentId(Long commentId) {
        this.commentoId = commentId;
    }
    
}
