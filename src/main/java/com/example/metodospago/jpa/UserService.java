package com.example.metodospago.jpa;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class UserService {

    @Inject
    private EntityManager em;

    public UserService() {}

    public void createUser(String name, String lastName,String email, String phone,String country, String password) {
        try {
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setCountry(country);
            user.setPassword(password);

            em.persist(user);
        } catch (PersistenceException e) {
            throw new IllegalStateException("El email  '" + email + "' ya existe.", e);
        }
    }


    public  void insertUserGame(int id, Game game) {
        User user = em.find(User.class, id);
        if(user == null) {
            throw new IllegalArgumentException("User id " + id + " not found.");
        }else {
            user.addGame(game);
        }
        em.merge(user);
    }

    public User findUserByEmailPass(String email,String password) {
        try {
            return em.createQuery("SELECT  e from  User e where e.email= :email and  e.password = : password",User.class).setParameter("email", email).setParameter("password", password).getSingleResult();
        }catch (PersistenceException e) {
            throw new IllegalStateException("E-mail or password incorrect please try again.  \n Or register. \n"+"For register please wrote your information in the url: \n" +
                    "/pay/register/your_name,your_lastName,your_email,your_phone,your_country,your_password", e);
        }
    }
    public List<User> listAllUsers() {
        return em.createQuery("SELECT  e from  User e",User.class).getResultList();
    }

    public User findUserById(int id) {
        return em.find(User.class, id);
    }

    public void deleteUser(int id) {
        User user = findUserById(id);
        em.remove(user);
    }

    public void updateUser(User user) {
        //em.getTransaction().begin();
        em.merge(user);
        //em.getTransaction().commit();
    }

}