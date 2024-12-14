package jpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameService {

    private EntityManager em;

    public GameService() {}

   public GameService(EntityManager em) {
        this.em = em;
    }

    public void createGame(String title, String description, double price,User user) {
        try {
            Game game = new Game();
            game.setTitle(title);
            game.setDescription(description);
            game.setPrice(price);
            game.setUser(user);
            em.persist(game);
        }catch (PersistenceException e) {
            throw new IllegalStateException("El juego con el título '" + title + "' ya existe.",e);
        }
    }

    public String showGames(){
        String games = "";
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

        return  games;
    }

}
