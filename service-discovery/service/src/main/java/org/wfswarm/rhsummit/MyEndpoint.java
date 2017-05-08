package org.wfswarm.rhsummit;

import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.wildfly.swarm.topology.Advertise;

/**
 * @author Ken Finnigan
 */
@Path("/welcome")
@Advertise("mp-service")
public class MyEndpoint {
    private static final JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);

    @Inject
    private MessageGenerator generator;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject welcome(@Context UriInfo uri) throws Exception {
        return jsonFactory.createObjectBuilder()
                .add("message", generator.getMessage("World"))
                .add("service-info", serviceInfo(uri))
                .build();
    }

    private String serviceInfo(UriInfo uri) throws UnknownHostException {
        String host = System.getenv("HOSTNAME");
        return host != null ? host : "localhost on port " + uri.getBaseUri().getPort();
    }
}
