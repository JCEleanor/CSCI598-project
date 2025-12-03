
import java.util.List;
import java.util.Scanner;


public class CashOnDeliveryPayment implements PaymentStrategy {

    private Integer pinCode;
    protected StringBuilder errorMsg = new StringBuilder("Error:COD not available in this location.");

    @Override
    public void pay(double amount) {
        System.out.println("Payment will be done at the time of delivery");
    }

    @Override
    public boolean verify() {
        List<Integer> codAvailablePincodes = List.of(80001, 80002, 80003);
        if (!codAvailablePincodes.contains(pinCode)) {
            System.out.println(errorMsg.toString());
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void fillInformation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter PIN code");
        pinCode = sc.nextInt();
    }
}