

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CardPayment implements PaymentStrategy {
    protected String cardNumber;
    protected String expDate;
    protected int cvv;
    protected String nameOnCard;
    protected boolean paymentVerfied = true;
    protected StringBuilder errorMsg = new StringBuilder("Error:Invalid card details-");

    public CardPayment(String cardNumber, String expDate, int cvv, String nameOnCard) {
        if (cardNumber.length() == 12) {
            this.cardNumber = cardNumber;
        } else {
        errorMsg.append("(Card number should be 12 digits);");
        paymentVerfied = false;
        }

        if (expDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth expiry = YearMonth.parse(expDate, formatter);
            YearMonth now = YearMonth.now();

            if (expiry.isAfter(now)) {
                this.expDate = expDate;
            } else {
                errorMsg.append("(Card is expired);");
                paymentVerfied = false;
            }
        } else {
            errorMsg.append("(Enter expiration date);");
            paymentVerfied = false;
        }

        if (cvv >= 100 && cvv <= 999) {
            this.cvv = cvv;
        } else {
            errorMsg.append("(CVV should be 3 digits);");
            paymentVerfied = false;
        }

        if (nameOnCard != null && !nameOnCard.isEmpty()) {
            this.nameOnCard = nameOnCard;
        } else {
            errorMsg.append("(Enter name on card);");
            paymentVerfied = false;
        }
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment of amount: " + amount + " is successful!");
    }

    @Override
    public boolean verify() {
        
        if(paymentVerfied==false){
        System.out.println(errorMsg.toString());
        }
        return paymentVerfied;
    }

}


class CreditCardPayment extends CardPayment {

    CreditCardPayment(String cardNumber, String expDate, int cvv, String nameOnCard) {
        super(cardNumber, expDate, cvv, nameOnCard);
    }

    @Override
    public void pay(double amount) {
        double finalAmount = amount + (amount * 0.03);
        System.out.println("Payment of amount: " + finalAmount +
                " is successful! (3% tax is added for credit card usage.)");
    }
}

class DebitCardPayment extends CardPayment {

    DebitCardPayment(String cardNumber, String expDate, int cvv, String nameOnCard) {
        super(cardNumber, expDate, cvv, nameOnCard);
    }
}


