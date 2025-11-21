
import java.util.List;


public class UPIPayment implements PaymentStrategy {
    protected boolean paymentVerfied = true;
    protected String upiId;
    protected StringBuilder errorMsg = new StringBuilder("Error:Invalid UPI details-(Please enter valid UPIID {fromat:name@<bankname>})");

    public UPIPayment(String upiId) {

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

            if (!user.isEmpty() && !provider.isEmpty() && validProviders.contains(provider.toLowerCase())) {

            
                    this.upiId = upiId;



            } else {
                
                paymentVerfied = false;    
            }
            }
            else{
               
                paymentVerfied = false;

            }

        } else {
            
            paymentVerfied = false;         
        }
    }

    @Override
    public void pay(double amount) {
 
        System.out.println("Paid " + amount + " using UPI: " + upiId);
    }

    @Override
    public boolean verify() {
        if(paymentVerfied==false){
        System.out.println(errorMsg.toString());
        }
        return paymentVerfied;
    }

    
}


class PayPalUPIPayment extends UPIPayment
{

    public PayPalUPIPayment(String upiId) {
        super(upiId);
    }
    
}
class GooglePayUPIPayment extends UPIPayment
{

    public GooglePayUPIPayment(String upiId) {
        super(upiId);
    }
    
}