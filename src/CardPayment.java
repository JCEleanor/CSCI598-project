

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class CardPayment implements PaymentStrategy {
    protected String cardNumber;
    protected String expDate;
    protected int cvv;
    protected String nameOnCard;
    protected boolean paymentVerfied = true;
    public CardPayment(String cardNumber, String expDate, int cvv, String nameOnCard) {
        if (cardNumber.length() == 12) {
            this.cardNumber = cardNumber;
        } else {
        paymentVerfied = false;
        }

        if (expDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth expiry = YearMonth.parse(expDate, formatter);
            YearMonth now = YearMonth.now();

            if (expiry.isAfter(now)) {
                this.expDate = expDate;
            } else {
                paymentVerfied = false;
            }
        } else {
            paymentVerfied = false;
        }

        if (cvv >= 100 && cvv <= 999) {
            this.cvv = cvv;
        } else {
            paymentVerfied = false;
        }

        if (nameOnCard != null && !nameOnCard.isEmpty()) {
            this.nameOnCard = nameOnCard;
        } else {
            paymentVerfied = false;
        }
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment of amount: " + amount + " is successful!");
    }

    @Override
    public boolean verify() {

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
