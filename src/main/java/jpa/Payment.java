package jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {

    @Id
    private int id;

    private String name;
    private double money;
    private int destiny;
    private String paymentType;

    public Payment(int id, String name, double money, int destiny, String paymentType) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.destiny = destiny;
        this.paymentType = paymentType;
    }

    public Payment() {}

    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }

    public int getDestiny() {
        return destiny;
    }
    public void setDestiny(int destiny) {
        this.destiny = destiny;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
