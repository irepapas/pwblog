/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.boundary;

import blog.control.ArticoloStore;
import blog.control.CommentiStore;
import blog.control.UserStore;
import blog.dto.ArticoloCreate;
import blog.dto.UserUpdate;
import blog.entity.Articolo;
import blog.entity.Commento;
import blog.entity.User;
import java.util.ArrayList;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Irene
 */
@RolesAllowed({"ADMIN", "USER"})
public class UserResource {
    
    @Inject
    private ArticoloStore articoloStore;
    @Inject
    private CommentiStore commentiStore;
    @Inject
    private UserStore userStore;
    
    private Long userId;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User find() {
        User u = userStore.find(userId).orElseThrow(()->new NotFoundException());
        return u;
    }
    
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(UserUpdate u) {
        User ut = userStore.find(userId).orElseThrow(() -> new NotFoundException());
        User updated = userStore.update(ut, u);
        return updated;
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response elimina() {
        User user = userStore.find(userId).orElseThrow(() -> new NotFoundException());
        userStore.elimina(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @RolesAllowed({"ADMIN"})
    @PATCH
    public Response bloccaUser() {
        userStore.find(userId).orElseThrow(() -> new NotFoundException());
        userStore.bloccato(userId);
        return Response.status(Response.Status.ACCEPTED).build();
    }
    @RolesAllowed({"ADMIN"})
    @POST
    @Path("articles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createArticle(ArticoloCreate article, ArrayList<String> tags) {
        User user = userStore.find(userId).orElseThrow(() -> new NotFoundException());
        Articolo saved = articoloStore.create(new Articolo(article), tags);
        return Response.status(Response.Status.CREATED)
                .entity(saved)
                .build();
    }
    @POST
    @Path("comments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createComment(Commento comment, Long articleId) {
        User user = userStore.find(userId).orElseThrow(() -> new NotFoundException());
        Commento saved = commentiStore.create(comment);
        saved.setUserId(userId);
        return Response.status(Response.Status.CREATED)
                .entity(saved)
                .build();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
