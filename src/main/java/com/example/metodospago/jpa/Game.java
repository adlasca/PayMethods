package com.example.metodospago.jpa;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private double price;
    @ManyToMany(mappedBy = "games", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonbTransient
    private List<User> users;

    public Game() {}
    public Game(int id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public List<User> getUser() {
        return users;
    }

    public void removeUser(User user) {
        if (users != null) {
            users.remove(user);
            user.getGames().remove(this);
        }
    }

    public void setUser(User user) {
        if (users == null) {  // Protección adicional
            users = new ArrayList<>();
        }
        this.users.add(user);
       // user.getGames().add(this);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", users=" + users +
                '}';
    }
}
