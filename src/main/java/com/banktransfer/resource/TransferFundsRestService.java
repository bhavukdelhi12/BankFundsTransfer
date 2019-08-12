package com.banktransfer.resource;

import com.banktransfer.service.TransferFunds;
import com.banktransfer.service.impl.SavingTransferFundsImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * This class represents the resource/API layer for transferring of funds between two
 * accounts.
 */
@Path("/")
public class TransferFundsRestService {

    private TransferFunds transferFunds = new SavingTransferFundsImpl();

    @GET
    @Path("/transfer")
    public Response hello(@QueryParam("from") int from, @QueryParam("to") int to,
                          @QueryParam("amount") int amount) {

        try {
            transferFunds.transferFunds(from, to, amount);
        } catch (final Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("Transfer of " +
                amount + "$ " + "from account ID: " + from + " to account ID: " + to +
                "was successful").build();
    }
}

