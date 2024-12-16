package com.example.metodospago.classes;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("transfer")
public class Transfer implements Pay {

    long accountNumber;
    int amount;
    String BankName;

    public Transfer() {
    }

    public Transfer(long accountNumber, int amount, String BankName) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.BankName = BankName;
    }
    @Override
    public String paying(double amount) {
        return "Transfer payment from account: "+accountNumber +" With amount of "+amount+ "successful";
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }
}
