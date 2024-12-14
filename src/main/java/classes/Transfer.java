package classes;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("transfer")
public class Transfer implements Pay {


    @Override
    public String paying() {
        return "Transfer payment successful";
    }
}
