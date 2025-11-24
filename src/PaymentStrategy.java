
public interface PaymentStrategy {
    void pay(double amount);
    boolean verify();

    void fillInformation();

}
