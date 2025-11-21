
import java.util.List;



public class WalletPayment implements PaymentStrategy {
    protected String emailId;
    protected boolean paymentVerfied = true;
    protected StringBuilder errorMsg = new StringBuilder("Error:Invalid Wallet details-(Please enter valid emailID {fromat:name@<account>.com})");
    public WalletPayment(String emailId) {

       if (emailId != null && emailId.contains("@")) {

            List<String> allowedDomains = List.of("gmail.com", "yahoo.com", "outlook.com","mines.edu");

            String[] parts = emailId.split("@");
            

            if (parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {

                String domain = parts[1].toLowerCase();

                if (allowedDomains.contains(domain)) {
                    this.emailId = emailId;   
                } else {
                    paymentVerfied = false;   
                }

            } else {
                paymentVerfied = false;       
            }

        } else {
            paymentVerfied = false;           
        }
    }

    @Override
    public void pay(double amount) {

        System.out.println("Paid " + amount + " using Wallet");
        
    }
    @Override
    public boolean verify() {
       if(paymentVerfied==false){
        System.out.println(errorMsg.toString());
        }
        return paymentVerfied;
    }


}

class AppleWalletPayment extends WalletPayment
{

    public AppleWalletPayment(String emailId) {
        super(emailId);
    }
    @Override
    public void pay(double amount) {

        System.out.println("Paid " + amount + " using Apple Wallet");
        
    }
    
}

class SamsungWalletPayment extends WalletPayment
{

    public SamsungWalletPayment(String emailId) {
        super(emailId);
    }
    @Override
    public void pay(double amount) {

        System.out.println("Paid " + amount + " using Samsung Wallet");
        
    }
    
}