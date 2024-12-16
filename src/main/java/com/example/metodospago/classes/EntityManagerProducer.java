package com.example.metodospago.classes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer {
    //Al ver que en cada metodo del Resource se repetia la necesidad de crear un EntityManager para los servicios,
    //se creo una clase que produce EntityManager gracias a un video de Youtube que encontre
    //Gracias a esto no es necesario generar nuevas instancias de EntityManager para cada Service
    //Pero por alguna razon ya no se crean las tablas en la base de datos, pero si funciona en el POSTMAN
    @PersistenceUnit(unitName = "PaymentService")
    private EntityManagerFactory emf;

    public void init(){
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("PaymentService");
        }
    }

    @Produces
    @Default
    @Dependent
    public EntityManager createEntityManager() {

        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }

    }
}
