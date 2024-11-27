package com.example.metodospago;

import classes.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jpa.PaymentsService;

@Path("/pay")
public class HelloResource {

    @Inject
    @QualifierPayment("creditcard")
    Pay visa;

    @Inject
    @QualifierPayment("paypal")
    Pay payPal;

    @Inject
    @QualifierPayment("transfer")
    Pay transfer;

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();

        PaymentsService paymentsService = new PaymentsService(em);

        em.getTransaction().begin();
        System.out.println("Inicio transaction");
        paymentsService.createPayments(1,"Pedro",12.5,123456,transfer.notifying());
        System.out.println("Fin transaccion");
        em.getTransaction().commit();


        return transfer.notifying();
    }
}