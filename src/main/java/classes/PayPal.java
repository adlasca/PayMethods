package classes;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Qualifier;
import jakarta.inject.Scope;
import jakarta.transaction.TransactionScoped;

import java.io.Serializable;

@SessionScoped
@QualifierPayment("paypal")
public class PayPal implements Notify, Serializable {
    @Override
    public String notifying() {
        return "PayPal payment successful";
    }
}
