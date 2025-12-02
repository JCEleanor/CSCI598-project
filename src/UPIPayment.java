
import java.util.List;
import java.util.Scanner;


public class UPIPayment implements PaymentStrategy {
    protected boolean paymentVerfied = true;
    protected String upiId;
    protected StringBuilder errorMsg = new StringBuilder("Error:Invalid UPI details-(Please enter valid UPIID {fromat:name@<bankname>})");

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI: " + upiId);
    }

    @Override
    public boolean verify() {
        if (upiId != null && upiId.contains("@")) {
            String[] parts = upiId.split("@");
            List<String> validProviders = List.of(
                    "oksbi",
                    "ybl",
                    "okicici",
                    "okhdfcbank",
                    "axl"
            );
            if(parts.length == 2){
                String user = parts[0];
                String provider = parts[1];
                if (user.isEmpty() || provider.isEmpty() || !validProviders.contains(provider.toLowerCase())) {
                    paymentVerfied = false;
                }
            }
            else{
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter UPI ID: ");
        upiId = sc.nextLine();
        sc.close();
    }
}


class PayPalUPIPayment extends UPIPayment
{
    
}
class GooglePayUPIPayment extends UPIPayment
{
    
}