package classes;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPayment("transfer")
public class Transfer implements Notify{
    @Override
    public String notifying() {
        return "Transfer payment successful";
    }
}
