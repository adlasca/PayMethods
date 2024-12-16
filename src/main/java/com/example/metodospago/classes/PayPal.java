package com.example.metodospago.classes;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.List;

@SessionScoped
@QualifierPayment("paypal")
public class PayPal implements Pay, Serializable {
    //Al necesitar tarjetas de credito, PayPal se convertiria en una entidad de la misma forma
    private List<CreditCard> cardArray;
    String name;

    public PayPal() {
    }
    public PayPal(List<CreditCard> cardArray, String name) {
        this.cardArray = cardArray;
        this.name = name;
    }
    public List<CreditCard> getCardArray() {
        return cardArray;
    }
    public void setCardArray(List<CreditCard> cardArray) {
        this.cardArray = cardArray;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void addCard(CreditCard card){
        cardArray.add(card);
    }
    public void removeCard(CreditCard card){
        cardArray.remove(card);
    }

    @Override
    public String paying(double amount) {
        return "PayPal payment successful";
    }
}
