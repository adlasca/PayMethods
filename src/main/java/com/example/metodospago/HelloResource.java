package com.example.metodospago;

import com.example.metodospago.classes.CreditCard;
import com.example.metodospago.classes.Pay;
import com.example.metodospago.classes.QualifierPayment;
import com.example.metodospago.jpa.*;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

import java.time.LocalDate;

@Path("/pay")
@Produces("application/json")
@Consumes("application/json")
@Transactional
public class HelloResource {


    @Inject
    @QualifierPayment("creditCard")
    Pay visa;

    @Inject
    @QualifierPayment("paypal")
    Pay payPal;

    @Inject
    @QualifierPayment("transfer")
    Pay transfer;

    UserService userService;

    GameService gameService;

    @Inject
    Receipt receipt;

    @Inject
    public HelloResource(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    public HelloResource() {
    }

    @GET
    @Produces("text/plain")
    @Path("/creditCard")
    public String creditCardPayment() {
        //En la teoría, la tarjeta de credito estaria con el usuario, el cual seria llamado por un metodo findCreditcard
        User user = new User();
        user = userService.findUserById(1);

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

        return visa.paying(receipt.calculate(user));
    }

    @GET
    @Produces("text/plain")
    @Path("/paypal")
    public String payPalPayment() {
        User user = new User();
        user = userService.findUserById(1);
        //Como el metodo Paypal requiere de una tarjeta de credito, tomaría la tarjeta del usuario y la usaría;
        // Siendo casi lo mismo que el metodo de pago por tarjeta de credito
        return payPal.paying(receipt.calculate(user));
    }


    @GET
    @Produces("text/plain")
    @Path("transfer")
    public String transferPayment() {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        EntityManager em= emProducer.getEntityManager();*/

        User user = new User();
       // UserService us = new UserService(emProducer.getEntityManager());
       // em.getTransaction().begin();
        user = userService.findUserById(1);

        //em.close();
        return transfer.paying(receipt.calculate(user)) ;
    }

    @POST
    @Path("/insertGames")
    public Response insertGames( Game game) {
/*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        GameService gameService = new GameService(em);*/
        //Game game1 = gameService.createGame("The legend of  Zelda","An inmersive adventure game",59.99,null);
        /*gameService.createGame("Balatro","Balatro is a deck-building roguelite where you must play poker hands and earn chips to defeat enemy blinds.",7.99,null);
        gameService.createGame("The Last of Us Part I","Endure and survive. Experience the emotional storytelling and unforgettable characters in The Last of Us™, winner of over 200 Game of the Year awards, now available for PC.",59.99,null);
        gameService.createGame("Resident Evil 4 Remake","Featuring modernized gameplay, a reimagined storyline, and vividly detailed graphics, Resident Evil 4 marks the rebirth of an industry juggernaut.",24.99,null);
        gameService.createGame("Okami HD","Experience the critically acclaimed masterpiece with its renowned Sumi-e ink art style in breathtaking high resolution.",14.99,null);
      */gameService.insertGame(game);
        //gameService.insertGame(game1);
 //       em.getTransaction().commit();
  //      em.close();
        return Response.created(URI.create("/games")).build();
    }

    @POST
    @Produces("text/plain")
    @Path("/register/{name},{lastName},{email},{phone},{country},{password}")
    public Response Register(@PathParam("name")String name, @PathParam("lastName")String lastName,
                             @PathParam("email")String email, @PathParam("phone")String phone,
                             @PathParam("country")String country, @PathParam("password")String password) {
/*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserService us = new UserService(em);*/
        //us.createUser("Pedro", "Picapiedra", "pedriño@piedra.com","0123456789","Rock City","roquita123");
        userService.createUser(name,lastName,email,phone,country,password);
      //  em.getTransaction().commit();
      //  em.close();
        return Response.ok().build();
    }

    @GET
    @Produces("application/json")
    @Path("/showUsers")
    public List<User> showUsers() {
        return userService.listAllUsers();
    }

    @GET
    @Produces("application/json")
    @Path("/showGames")
    public List<Game> showGames() {

     /*   EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        gameService = new GameService(em);*/
        //String games = gameService.showGames();
        //em.close();
        return gameService.showGames();
    }

    @POST
    @Produces("text/plain")
    @Path("/buyGame/{email}/{password}/{id}")
    public String buyGame(@PathParam("email")String email,@PathParam("password")String password,@PathParam("id")int id) {
       /* EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        gameService = new GameService(em);
        UserService us = new UserService(em);*/
        User user = userService.findUserByEmailPass(email,password);
        Game game = gameService.findGameById(id);
        if (user == null) {
            return "User not found!";
        }
        if (game == null) {
            return "Game not found!";
        }
        user.getGames().add(game);
        userService.updateUser(user);
        //em.getTransaction().commit();
        //String games = gameService.showGames();
       // em.close();
        return "\n Game added";
    }

    @GET
    @Produces("text/plain")
    @Path("/login/{email}/{password}")
    public String login(@PathParam("email")String email, @PathParam("password")String password) {
       /* EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentService");
        EntityManager em = emf.createEntityManager();
        UserService us = new UserService(em);*/
        User user= userService.findUserByEmailPass(email, password);

        if(user == null) {
            return "E-mail or password incorrect please try again. \n   Or register";
        }else {
            return "Login successful";
        }
    }

    @GET
    @Produces("text/plain")
    @Path("/receipt/{id}")
    public String logout(@PathParam("id") int id) {
        double total=0;
        User user= userService.findUserById(id);
        receipt.calculate(user);

        return "Logout successful";
    }

}