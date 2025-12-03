import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {

    static Map<String,Product> productTypes;
    static Map<String,PaymentStrategy> paymentOptions;

    //TODO Might need to change the map in InvManager to use strings to map to a tuple of product and qtn

    public static void fillAssocMaps(){
        productTypes = new HashMap<String,Product>();
        paymentOptions = new HashMap<String,PaymentStrategy>();

        productTypes.put("Electronic",new ElectronicProduct());
        productTypes.put("Perishable",new PerishableProduct());
        paymentOptions.put("Debit Card",new CardPayment());
        paymentOptions.put("Credit Card",new CreditCardPayment());
        paymentOptions.put("CashOnDelivery",new CashOnDeliveryPayment());
        paymentOptions.put("Gift Card",new GiftCardPayment());
        paymentOptions.put("Google Pay",new GooglePayUPIPayment());
        paymentOptions.put("Pay Pal",new PayPalUPIPayment());
        paymentOptions.put("Apple", new AppleWalletPayment());
        paymentOptions.put("Samsung", new SamsungWalletPayment());
    }

    private static void sellProduct(InvManager inventory){
        Scanner scanner = new Scanner(System.in);

        System.out.println("What type of product do you want to sell?");
        String type = scanner.nextLine();
        Product userProduct = productTypes.get(type).clone();
        userProduct.setProductInfo();

        System.out.println("How much do you want to sell?");
        int qtnIn = scanner.nextInt();
        inventory.addProductToInv(userProduct,qtnIn);
    }

    private static void buyProduct(InvManager inventory) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here is a list of products, what do you want to buy?");
        inventory.displayAllProducts();
        String productIn = scanner.next();
        System.out.println("How much do you want to buy?");
        int qtnIn = scanner.nextInt();
        System.out.println("How do you want to pay?");
        String userOption = scanner.next();
        PaymentStrategy paymentMethod = paymentOptions.get(userOption);

        //Change payment methods to get information by themselves (call them here)
        PaymentProcessor payProcessor = new PaymentProcessor();
        payProcessor.setPaymentMethod(paymentMethod);
        payProcessor.populatePaymentInfo();
        payProcessor.validate();
        if (inventory.sell(/*Product*/, qtnIn)) {
            int productTotal = /*Product*/.getPrice();
            payProcessor.checkout(productTotal);
        } else {
            System.out.println("Not enough stock");
        }
    }

    public static void main(String[] args) {
        InvManager inventory = InvManager.getInstance();
        fillAssocMaps();

        Scanner scanner = new Scanner(System.in);
<<<<<<< Updated upstream
        label:
=======
        System.out.println("Welcome to E-Trading. Are you here to sell or to buy today?");
        System.out.println("If you would like to exit type exit");
>>>>>>> Stashed changes
        while(true) {
            String userIn = scanner.next();

            //Maybe able to divide this if into seperate classes
            switch (userIn) {
                case "sell":
                    sellProduct(inventory);
                    break;
                case "buy":
                    buyProduct(inventory);
                    break;
                case "exit":
                    break label;
                default:
                    System.out.println("Not a valid option, either use sell, buy, or exit");
                    break;
            }
        }
    }
}
