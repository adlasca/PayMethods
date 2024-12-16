package com.example.metodospago.jpa;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class GameService {

    @Inject
    private EntityManager em;

    public GameService() {
    }

    public Game insertGame(Game game) {
        em.persist(game);
        return game;
    }

    public Game createGame(String title, String description, double price,User user) {
        Game game = new Game();
           // em.getTransaction().begin();

            game.setTitle(title);
            game.setDescription(description);
            game.setPrice(price);
            game.setUser(user);
            //em.persist(game);
            //em.getTransaction().commit();
            //em.close();
        return game;
    }

    public List<Game> showGames(){
        String games = "";
        //em.getTransaction().begin();
        List<Game> gamesList = em.createQuery("SELECT  e FROM Game e", Game.class).getResultList();
        if(!gamesList.isEmpty()){
            for (Game g : gamesList) {
                games+="-------------------"+"\n";
                games+="Id: "+g.getId()+"\n";
                games+="Title: "+g.getTitle()+"\n";
                games += "Description: "+ g.getDescription() + "\n";
                games += "Price: "+g.getPrice() + "\n";
            }
        }else games = "No games found";

        return  gamesList;
    }

    public Game findGameById(int id) {
        return em.find(Game.class, id);
    }

    public void deleteGame(int id) {
        Game game = findGameById(id);
        em.remove(game);
    }

    public void updateGame(Game game) {
        //em.getTransaction().begin();
        em.merge(game);
        //em.getTransaction().commit();
    }


}