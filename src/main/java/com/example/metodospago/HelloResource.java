package com.example.metodospago;

import classes.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/pay")
public class HelloResource {

    @Inject
    @QualifierPayment("creditcard")
    Notify visa;

    @Inject
    @QualifierPayment("paypal")
    Notify payPal;

    @Inject
    @QualifierPayment("transfer")
    Notify transfer;

    @GET
    @Produces("text/plain")
    @Path("/creditcard")
    public String creditCardPayment() {
        return visa.notifying();
    }
    @GET
    @Produces("text/plain")
    @Path("/paypal")
    public String payPalPayment() {
        return payPal.notifying();
    }
    @GET
    @Produces("text/plain")
    @Path("transfer")
    public String transferPayment() {
        return transfer.notifying();
    }
}