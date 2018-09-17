package com.revolut.money.transfer.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transfer")
public class TransferController {

	@POST
    @Path("move/{fromAccountId}/{")
    @Produces(MediaType.APPLICATION_JSON)
    public String test(@PathParam(value="id") String id) {
        return "Test "+id;
    }
}
