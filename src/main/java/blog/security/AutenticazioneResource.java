/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.security;

import blog.control.UserStore;
import blog.entity.User;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Irene
 */
 @Path("/auth")
public class AutenticazioneResource {
    

    @Inject
    UserStore store;

    @Inject
    JWTManager jwtManager;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("email") String email, @FormParam("pwd") String pwd) {
        User found = store.findByEmailAndPwd(email, pwd).orElseThrow(() -> new NotAuthorizedException("invalid email or password", Response.status(Response.Status.UNAUTHORIZED).build()));
        return Response.ok().entity(jwtManager.generate(found)).build();
    }
}
    

