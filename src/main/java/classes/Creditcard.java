package classes;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@QualifierPayment("creditcard")
public class Creditcard implements Notify{
    @Override
    public String notifying() {
        return "CreditCard payment successful";
    }
}
