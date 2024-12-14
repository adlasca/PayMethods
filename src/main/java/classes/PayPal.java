package classes;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

@SessionScoped
@QualifierPayment("paypal")
public class PayPal implements Pay, Serializable {

    private List<CreditCard> cardArray;
    String name;

    @Override
    public String paying() {
        return "PayPal payment successful";
    }
}
