

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CardPayment implements PaymentStrategy {
    protected String cardNumber;
    protected String expDate;
    protected int cvv;
    protected String nameOnCard;
    protected boolean paymentVerfied = true;
    protected StringBuilder errorMsg = new StringBuilder("Error:Invalid card details-");

    @Override
    public void pay(double amount) {
        System.out.println("Payment of amount: " + amount + " is successful!");
    }

    @Override
    public boolean verify() {
        if (cardNumber.length() != 12) {
            errorMsg.append("(Card number should be 12 digits);");
            paymentVerfied = false;
        }

        if (expDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth expiry = YearMonth.parse(expDate, formatter);
            YearMonth now = YearMonth.now();
            if (!expiry.isAfter(now)){
                errorMsg.append("(Card is expired);");
                paymentVerfied = false;
            }
        } else {
            errorMsg.append("(Enter expiration date);");
            paymentVerfied = false;
        }

        if (!(cvv >= 100 && cvv <= 999)) {
            errorMsg.append("(CVV should be 3 digits);");
            paymentVerfied = false;
        }

        if (nameOnCard == null || nameOnCard.isEmpty()){
            errorMsg.append("(Enter name on card);");
            paymentVerfied = false;
        }
        return paymentVerfied;
    }

    @Override
    public void fillInformation() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the card number: ");
        String cardNumber = sc.nextLine();
        System.out.println("Please enter the expiration date: ");
        String expDate = sc.nextLine();
        System.out.println("Please enter the cvv: ");
        int cvv = sc.nextInt();
        System.out.println("Please enter the name on card: ");
        String nameOnCard = sc.nextLine();
        sc.close();
    }

}


class CreditCardPayment extends CardPayment {

    @Override
    public void pay(double amount) {
        double finalAmount = amount + (amount * 0.03);
        System.out.println("Payment of amount: " + finalAmount +
                " is successful! (3% tax is added for credit card usage.)");
    }
}


