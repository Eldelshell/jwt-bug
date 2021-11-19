package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.enterprise.context.ApplicationScoped;


@Path("/hello")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @POST
    @Path("/insecure")
    public Response helloInsecure() {
        return Response.ok().build();
    }
    
    @POST
    @Path("/secured/works")
    @RolesAllowed({"User"})
    public Response helloSecured() {
        return Response.ok().build();
    }
    
    @POST
    @Path("/secured/bugged")
    @RolesAllowed({"User"})
    public Response helloSecured(@Valid MyRequest req) {
        System.out.println("You shouldn't be here");
        System.out.println(req);
        return Response.ok("{\"message\":\"you shouldn't be here\"}").build();
    }
    
    private static class MyRequest {
        public String name;
    }
}
