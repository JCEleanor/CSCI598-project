import java.util.Map;

public class GiftCardPayment implements PaymentStrategy
{

    protected boolean paymentVerfied = true;
    protected String giftCardCode;
    protected int giftCardPIN;
    protected double amount;
    protected StringBuilder errorMsg = new StringBuilder("Error:Invalid Giftcard details-");


    public GiftCardPayment(String giftCardCode, int giftCardPIN,double amount)
    {
          
        if (giftCardCode != null || !giftCardCode.isEmpty())
        {

            giftCardCode = giftCardCode.trim().toUpperCase();
            if(giftCardCode.matches("^[A-Z0-9]{12,16}$") && giftCardPIN >= 1000 || giftCardPIN <= 9999)
            {
                Map<String, Double> giftCardRegistry = Map.of(
                "GGWRWEFWERFWR", 100.0,
                "WQEQERWERFWR", 70.0
                );
                
                Double balance =giftCardRegistry.get(giftCardCode);
                if(balance != null && amount <= balance)
                {
                        this.giftCardCode = giftCardCode;
      
                }
                else
                {
                    errorMsg.append("(Gift card not eligible or Balance too low)");
                    paymentVerfied = false;
                }


            }
            else
            {
                errorMsg.append("(Please enter correct GitCard details, the pinCode should be 4 digits)");
                paymentVerfied = false;
            }
        }
        else
        {
            errorMsg.append("(Please enter correct GitCard details)");
            paymentVerfied = false;
        }

    }
    @Override
    public void pay(double amount) {

        System.out.println("Paid " + amount + " using GiftCard: " + giftCardCode);
        
    }
    @Override
    public boolean verify() {
       if(paymentVerfied==false){
        System.out.println(errorMsg.toString());
        }
        return paymentVerfied;
    }

    
}