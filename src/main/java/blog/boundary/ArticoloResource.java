/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.boundary;

import blog.control.ArticoloStore;
import blog.control.CommentiStore;
import blog.dto.ArticoloUpdate;
import blog.entity.Articolo;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Irene
 */
@RolesAllowed({"ADMIN"})
public class ArticoloResource {
    
    @Inject
    private ArticoloStore articoloStore;

    @Inject
    private CommentiStore commentiStore;

    private Long articleId;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Articolo find() {
        Articolo art = articoloStore.find(articleId).orElseThrow(() -> new NotFoundException());
        return art;
    }

    @RolesAllowed({"ADMIN"})
    @PATCH
    @Path("articles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Articolo updateArticolo(ArticoloUpdate artUp) {
        Articolo found = articoloStore.find(articleId).orElseThrow(() -> new NotFoundException());
        Articolo updated = articoloStore.update(found, artUp);
        return updated;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Articolo> articoliPerPeriodo(LocalDateTime start, LocalDateTime stop) {
        return articoloStore.cercaPeriodo(start, stop);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Articolo> allByTitle(String titolo) {
        return articoloStore.findByTitle(titolo);
    }


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    
}
