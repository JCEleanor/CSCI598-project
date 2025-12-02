
import java.util.List;
import java.util.Scanner;


public class WalletPayment implements PaymentStrategy {
    protected String emailId;
    protected boolean paymentVerfied = true;
    protected StringBuilder errorMsg = new StringBuilder("Error:Invalid Wallet details-(Please enter valid emailID {fromat:name@<account>.com})");

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Wallet");
    }
    @Override
    public boolean verify() {
        if (emailId != null && emailId.contains("@")) {
            List<String> allowedDomains = List.of("gmail.com", "yahoo.com", "outlook.com","mines.edu");
            String[] parts = emailId.split("@");
            if (parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                String domain = parts[1].toLowerCase();
                if (! allowedDomains.contains(domain)) {
                    paymentVerfied = false;
                }
            } else {
                paymentVerfied = false;
            }
        } else {
            paymentVerfied = false;
        }
       if(!paymentVerfied){
            System.out.println(errorMsg.toString());
       }
       return paymentVerfied;
    }

    @Override
    public void fillInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email");
        String emailId = scanner.nextLine();
        scanner.close();
    }


}

class AppleWalletPayment extends WalletPayment
{
    @Override
    public void pay(double amount) {

        System.out.println("Paid " + amount + " using Apple Wallet");
        
    }
    
}

class SamsungWalletPayment extends WalletPayment
{
    @Override
    public void pay(double amount) {

        System.out.println("Paid " + amount + " using Samsung Wallet");
        
    }
    
}