package com.example.metodospago;

import classes.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jpa.*;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

@Path("/pay")
public class HelloResource {

    @Inject
    @QualifierPayment("credit card")
    Pay visa;

    @Inject
    @QualifierPayment("paypal")
    Pay payPal;

    @Inject
    @QualifierPayment("transfer")
    Pay transfer;

    @Inject
    UserService userService;

    @Inject
    GameService gameService;

    @GET
    @Produces("text/plain")
    @Path("/credit card")
    public String creditCardPayment() {
        CreditCard cc;
        cc = new CreditCard(1111,
                LocalDate.of(2027,12,12),
                123,
                "Visa",
                "Pedro",
                "Juarez",
                "Ecuador",
                "Quito",
                1234,
                "0987654321",
                "Eloy Alfaro",
                "Simon Bolivar");
        return visa.paying();
    }

    @GET
    @Produces("text/plain")
    @Path("/paypal")
    public String payPalPayment() {
        return payPal.paying();
    }


    @GET
    @Produces("text/plain")
    @Path("transfer")
    public String transferPayment() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();

        List<Game> gameList= new ArrayList<>();

        UserService us = new UserService(em);
        //GameService gameService = new GameService(em);
        em.getTransaction().begin();
        System.out.println("Inicio transaction");
        //gameList.add(gameService.createGame("TLOY","A game for you",12.5,null));

       // us.createUser("Pedro","Picapiedra","asdf@fsdd","1234567890","Gotham","1234asdf",gameList);
        System.out.println("Fin transaccion");
        em.getTransaction().commit();

        return transfer.paying();
    }

    @GET
    @Produces("text/plain")
    @Path("/rockGames")
    public String rockGames() {
        String welcome="-----WELCOME TO ROCK-GAMES----- \n"
                +"Pleas login or Register. \n"
                +"For login, in the url enter your email and password  like: \n/pay/login/your_email,your_password \n"
                +"For register please wrote your information in the url: \n" +
                "/pay/register/your_name,your_lastName,your_email,your_phone,your_country,your_password. \n";
        String games= "Please type in the url /pay/insertGames  and then comeback and login. ";
        return welcome +games;
    }

    @GET
    @Produces("text/plain")
    @Path("/insertGames")
    public String insertGames() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        GameService gameService = new GameService(em);
        gameService.createGame("The legend of  Zelda","An inmersive adventure game",59.99,null);
        gameService.createGame("Balatro","Balatro is a deck-building roguelite where you must play poker hands and earn chips to defeat enemy blinds.",7.99,null);
        gameService.createGame("The Last of Us Part I","Endure and survive. Experience the emotional storytelling and unforgettable characters in The Last of Us™, winner of over 200 Game of the Year awards, now available for PC.",59.99,null);
        gameService.createGame("Resident Evil 4 Remake","Featuring modernized gameplay, a reimagined storyline, and vividly detailed graphics, Resident Evil 4 marks the rebirth of an industry juggernaut.",24.99,null);
        gameService.createGame("Okami HD","Experience the critically acclaimed masterpiece with its renowned Sumi-e ink art style in breathtaking high resolution.",14.99,null);

        em.getTransaction().commit();
        em.close();
        return "Game inserted";
    }

    @GET
    @Produces("text/plain")
    @Path("/register/{name},{lastName},{email},{phone},{country},{password}")
    public String Register(@PathParam("name")String name,@PathParam("lastName")String lastName,
                           @PathParam("email")String email,@PathParam("phone")String phone,
                           @PathParam("country")String country,@PathParam("password")String password) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserService us = new UserService(em);
        //us.createUser("Pedro", "Picapiedra", "pedriño@piedra.com","0123456789","Rock City","roquita123");
        us.createUser(name,lastName,email,phone,country,password);
        em.getTransaction().commit();
        em.close();
        return "Register successful";
    }

    @GET
    @Produces("text/plain")
    @Path("/showGames")
    public String showGames() {

        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        //EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();
        //gameService = new GameService(em);
        String games = gameService.showGames();
       // em.close();
        return games+"\n Please enter your choice in the url /pay/buyGame/'id_game' ";
    }

    @GET
    @Produces("text/plain")
    @Path("/buyGame/{id}")
    public String buyGame(@PathParam("id")String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
     //   gameService = new GameService(em);
        String games = gameService.showGames();
        em.close();
        return games+"\n Please enter your choice in the url /pay/buy/'id_game' ";
    }
    @GET
    @Produces("text/plain")
    @Path("/login")
    public String login() {
        return "Please enter your email address and password in the url like /login/your_email,your_password";
    }


    @GET
    @Produces("text/plain")
    @Path("/login/{email},{password}")
    public String login(@PathParam("email")String email, @PathParam("password")String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        UserService us = new UserService(em);
        User user= us.findUserByEmailPass(email, password);

        if(user == null) {
            return "E-mail or password incorrect please try again. \n   Or register";
        }else {
            return "Login successful";
        }
    }

    @GET
    @Produces("text/plain")
    @Path("/pay/{method}")
    public String pay(@QueryParam("method" )String method) {

        switch (method){
            case "CreditCard":
                visa.paying();
                break;
            case "Paypal":
                payPal.paying();
                break;
            case "Transfer":
                transfer.paying();
                break;
            default:

                break;
        }
        return "User inserted";
    }

}