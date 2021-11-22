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
import javax.ws.rs.PATCH;
import javax.ws.rs.PUT;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    @POST
    @Path("/insecure")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloInsecure() {
        return Response.ok().build();
    }
    
    @POST
    @Path("/secured/works")
    @RolesAllowed({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloSecured() {
        return Response.ok().build();
    }
    
    @POST
    @Path("/secured/bugged")
    @RolesAllowed({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloSecuredPost(@Valid MyRequest req) {
        System.out.println("POST You shouldn't be here");
        System.out.println(req);
        return Response.ok("{\"message\":\"you shouldn't be here\"}").build();
    }
    
    @PATCH
    @Path("/secured/bugged")
    @RolesAllowed({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON_PATCH_JSON)
    public Response helloSecuredPatch(@Valid MyRequest req) {
        System.out.println("PATCH You shouldn't be here");
        System.out.println(req);
        return Response.ok("{\"message\":\"you shouldn't be here\"}").build();
    }
    
    @PUT
    @Path("/secured/bugged")
    @RolesAllowed({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloSecuredPut(@Valid MyRequest req) {
        System.out.println("PUT You shouldn't be here");
        System.out.println(req);
        return Response.ok("{\"message\":\"you shouldn't be here\"}").build();
    }

    @POST
    @Path("/secured/bugged-text")
    @RolesAllowed({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response helloSecured(String req) {
        System.out.println("You shouldn't be here");
        System.out.println(req);
        return Response.ok("{\"message\":\"you shouldn't be here\"}").build();
    }
    
    private static class MyRequest {
        public String name;
    }
}
