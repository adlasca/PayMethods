package jpa;

import jakarta.persistence.EntityManager;

public class PaymentsService {

    private EntityManager em;

    public PaymentsService(EntityManager em) {
        this.em = em;
    }

    public Payment createPayments(int id, String name, double money, int destiny, String paymentType) {
        Payment payment = new Payment();
        payment.setId(id);
        payment.setName(name);
        payment.setMoney(money);
        payment.setDestiny(destiny);
        payment.setPaymentType(paymentType);
        em.persist(payment);
        System.out.println(payment.getId());
        return payment;
    }
    public Payment findById(int id) {
        return em.find(Payment.class, id);
    }

    public void updatePayment(Payment payment) {
        em.getTransaction().begin();
        em.merge(payment);
        em.getTransaction().commit();
    }
    public void deletePayment(int id) {
        Payment payment = findById(id);
        em.remove(payment);
    }

}
