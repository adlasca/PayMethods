package jpa;

import classes.CreditCard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

import java.util.List;

@ApplicationScoped
public class UserService {
    @PersistenceContext(unitName = "PaymentService")
    private EntityManager em;

    public UserService() {}
    public UserService(EntityManager em) {
        this.em = em;
    }

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


    public  void insertUserGame(String email, Game game) {
        User user = em.find(User.class, email);
        if(user == null) {
            throw new IllegalArgumentException("User email " + email + " not found.");
        }else user.addGame(game);
        em.persist(user);
    }

    public User findUserByEmailPass(String email,String password) {
        try {
            return em.createQuery("SELECT  e from  User e where e.email= :email and  e.password = : password",User.class).setParameter("email", email).setParameter("password", password).getSingleResult();
        }catch (PersistenceException e) {
            throw new IllegalStateException("E-mail or password incorrect please try again.  \n Or register. \n"+"For register please wrote your information in the url: \n" +
                    "/pay/register/your_name,your_lastName,your_email,your_phone,your_country,your_password", e);
        }
    }

    public  void  addCreditCard(String email,CreditCard creditCard) {
        User user = em.find(User.class, email);
        if(user == null) {
            throw new IllegalArgumentException("User email " + email + " not found.");
        }else user.addCreditCard(creditCard);
        em.persist(user);
    }
}
