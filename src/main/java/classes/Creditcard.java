package classes;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@QualifierPayment("creditcard")
public class Creditcard implements Pay {
    @Override
    public String notifying() {
        return "CreditCard payment successful";
    }
}
