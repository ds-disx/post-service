package org.disx;

import java.util.List;
import java.util.Set;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/disxs")
@Transactional
public class DisxResource {

    @Inject
    DisxService DisxService;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    public List<Disx> getDisxs() {
        return DisxService.findAllDisxs();
    }

    @GET
    @Path("/user/{username}")
    public List<Disx> getDisxsByUsername(@PathParam("username") String username) {
        return DisxService.findDisxsByUsername(username);
    }

    @GET
    @Path("/search")
    public List<Disx> findDisxsByTitle(@QueryParam("title") String title) {
        return DisxService.findDisxsByTitle(title);
    }

    @GET
    @Path("/{id}")
    public Disx getDisxById(@PathParam("id") Long id) {
        return DisxService.findDisxById(id);
    }

    @POST
    @Authenticated
    public Response createDisx(Disx Disx) {
        DisxService.save(Disx);
        return Response.ok(Disx.getId()).build();
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public Response deleteDisxById(@PathParam("id") Long id) {
        Set<String> s = securityIdentity.getRoles();
        if (s.contains("admin")) {
            DisxService.deleteDisx(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
