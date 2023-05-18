package com.expentor.resource;

import com.expentor.dto.TransactionRequest;
import com.expentor.service.TransactionService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.Month;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/transactions")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TransactionResource {
    @Inject
    TransactionService service;

    String userId = "xr765yaTgzeCxcVRKmhoy7Ak7LL2";

    @Operation(summary = "Get a list of transactions of a user")
    @GET
    public Response getAllTransactions() {
        return Response.ok(service.getAllTransactions(userId)).build();
    }

    @Operation(summary = "Get a transaction")
    @Path("/{id}")
    @GET
    public Response getTransaction(@PathParam("id") long id) {
        return Response.ok(service.getTransaction(id, userId)).build();
    }

    @Operation(summary = "Add a transaction")
    @POST
    public Response addTransaction(TransactionRequest request) {
        return Response.ok(service.persistTransaction(userId, request)).build();
    }

    @Operation(summary = "Delete a transaction")
    @DELETE
    @Path("/{id}")
    public Response deleteTransaction(@PathParam("id") long id) {
        service.deleteTransaction(id, userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Operation(summary = "Update a transaction")
    @PUT
    @Path("/{id}")
    public Response updateTransaction(@PathParam("id") long id, TransactionRequest request) {
        return Response.ok(service.updateTransaction(id, userId, request)).build();
    }

    @Operation(summary = "Get a total monthly credited amount")
    @GET
    @Path("/credited")
    public Response getTotalMonthlyCredited(@QueryParam("month") String month, @QueryParam("year") String year) {
        return Response.ok(service.getTotalMonthlyCredited(userId, Month.valueOf(month), year)).build();
    }

    @Operation(summary = "Get a total monthly debited amount")
    @GET
    @Path("/debited")
    public Response getTotalMonthlyDebited(@QueryParam("month") String month, @QueryParam("year") String year) {
        return Response.ok(service.getTotalMonthlyDebited(userId, Month.valueOf(month), year)).build();
    }
}
