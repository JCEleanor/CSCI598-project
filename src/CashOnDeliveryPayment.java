
import java.util.List;


public class CashOnDeliveryPayment implements PaymentStrategy {

private Integer pinCode;
protected boolean paymentVerfied = true;
protected StringBuilder errorMsg = new StringBuilder("Error:COD not available in this location.");
public CashOnDeliveryPayment(Integer pinCode)
{

List<Integer> codAvailablePincodes = List.of(80001, 80002, 80003);
if (codAvailablePincodes.contains(pinCode)) {
    this.pinCode = pinCode;
} 
else {
    paymentVerfied = false;
}

}
@Override
    public void pay(double amount) {

        System.out.println("Payment will be done at the time of delivery");
        
    }
    @Override
    public boolean verify() {
         if(paymentVerfied==false){
        System.out.println(errorMsg.toString());
        }
        return paymentVerfied;
    }



}