package classes;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
@QualifierPayment("paypal")
public class PayPal implements Pay, Serializable {
    @Override
    public String notifying() {
        return "PayPal payment successful";
    }
}
