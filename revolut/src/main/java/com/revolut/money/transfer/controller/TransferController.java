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
    @Path("/move/{fromAccountId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String test(@PathParam(value="fromAccountId") String id) {
        return "Test "+id;
    }
}
