import java.util.Map;
import java.util.Scanner;

public class GiftCardPayment implements PaymentStrategy
{

    protected boolean paymentVerfied = true;
    protected String giftCardCode;
    protected int giftCardPIN;
    protected double amount;
    protected StringBuilder errorMsg = new StringBuilder("Error:Invalid Giftcard details-");

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using GiftCard: " + giftCardCode);
    }

    @Override
    public boolean verify() {
        if (giftCardCode != null || !giftCardCode.isEmpty()) {
            giftCardCode = giftCardCode.trim().toUpperCase();
            if(giftCardCode.matches("^[A-Z0-9]{12,16}$") && giftCardPIN >= 1000 || giftCardPIN <= 9999) {
                Map<String, Double> giftCardRegistry = Map.of(
                        "GGWRWEFWERFWR", 100.0,
                        "WQEQERWERFWR", 70.0
                );
                Double balance = giftCardRegistry.get(giftCardCode);
                if(balance == null || amount > balance) {
                    errorMsg.append("(Gift card not eligible or Balance too low)");
                    paymentVerfied = false;
                }
            } else{
                errorMsg.append("(Please enter correct GitCard details, the pinCode should be 4 digits)");
                paymentVerfied = false;
            }
        } else {
            errorMsg.append("(Please enter correct GitCard details)");
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
        System.out.println("Enter Gift Card Code: ");
        giftCardCode = sc.nextLine();
        System.out.println("Enter Gift Card PIN: ");
        giftCardPIN = sc.nextInt();
        System.out.println("Enter Gift Card Amount: ");
        amount = sc.nextDouble();
        sc.close();
    }
}