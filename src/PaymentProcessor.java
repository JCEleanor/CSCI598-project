
public class PaymentProcessor {
    private PaymentStrategy strategy;

    public PaymentProcessor() {    
    }
    public void setPaymentMethod(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(double amount) {
        if(strategy == null) {
            System.out.println("No payment method selected!");
            return;
        }
        strategy.pay(amount);
    }

    public void populatePaymentInfo(){
        if(strategy == null) {
            System.out.println("No payment method selected!");
            return;
        }
        strategy.fillInformation();
    }

    public void validate(){
        if(strategy == null) {
            System.out.println("No payment method selected!");
            return;
        }
        strategy.verify();
    }
}