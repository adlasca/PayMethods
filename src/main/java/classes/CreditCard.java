package classes;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jpa.User;

import java.time.LocalDate;

@RequestScoped
@QualifierPayment("credit card")
@Entity
public class CreditCard implements Pay {
    @Id
    private int numberCard;

    private LocalDate expirationDate;
    private int securityCode;
    private String cardType;
    private String name;
    private String lastName;
    private String country;
    private String city;
    private int zipCode;
    private String phoneNumber;
    private String Address1;
    private String Address2;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CreditCard() {}

    public CreditCard(int numberCard, LocalDate expirationDate, int securityCode,
                      String cardType, String name, String lastName, String country,
                      String city, int zipCode, String phoneNumber, String address1, String address2) {
        this.numberCard = numberCard;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.cardType = cardType;
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        Address1 = address1;
        Address2 = address2;
    }

    @Override
    public String paying() {
        return "CreditCard payment successful";
    }

    public int getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(int numberCard) {
        this.numberCard = numberCard;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }
}