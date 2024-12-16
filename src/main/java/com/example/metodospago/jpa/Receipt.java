package com.example.metodospago.jpa;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.List;

@SessionScoped
public class Receipt implements Serializable {

    private int id;

    private String name;
    private double total;
//    private Pay paymentType;
    private List<Game> game;
    private User user;

    public Receipt() {
    }

    public Receipt(int id, String name, double total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }
    public double calculate(User user){
        double total=0;
        List <Game> gameList=user.getGames();
        for (Game game : gameList) {
            total+=game.getPrice();
        }
        return total;
    }
}
