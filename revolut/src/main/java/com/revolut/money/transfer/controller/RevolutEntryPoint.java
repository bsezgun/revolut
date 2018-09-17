package com.revolut.money.transfer.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transfer")
public class RevolutEntryPoint {

	@GET
    @Path("test/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(@PathParam(value="id") String id) {
        return "Test "+id;
    }
}
