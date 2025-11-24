import java.util.Date;
import java.util.Scanner;

public class Client {

    //TODO Might need to change the map in InvManager to use strings to map to a tuple of product and qtn
    //TODO Need to change payment methods to ask user to fill themselves as they all will need different things
    //TODO Might want to change product to have a fill method to change all respective fields. Or maybe we could make a Product{Type}Filler Class
    //TODO Find a way to select product/payment type without forcing a switch/if (Possible?)

    private static void sellProduct(InvManager inventory){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What type of product do you want to sell?");
        //Put fill method inside of the products or new classes?

        System.out.println("How much do you want to sell?");
        int qtnIn = scanner.nextInt();
        inventory.addProductToInv(/*New product*/,qtnIn);
    }

    private static void buyProduct(InvManager inventory) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here is a list of products, what do you want to buy?");
        inventory.displayAllProducts();
        String productIn = scanner.next();
        System.out.println("How much do you want to buy?");
        int qtnIn = scanner.nextInt();
        System.out.println("How do you want to pay?");

        //Change payment methods to get information by themselves (call them here)
        PaymentProcessor payProcessor = new PaymentProcessor();
        payProcessor.setPaymentMethod(/*selected method*/);
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
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Welcome to E-Trading. Are you here to sell or to buy today?");
            System.out.println("If you would like to exit type exit");
            String userIn = scanner.next();

            //Maybe able to divide this if into seperate classes
            if(userIn == "sell") {
                sellProduct(inventory);
            }else if(userIn == "buy") {
                buyProduct(inventory);
            }else if(userIn == "exit"){
                break;
            }else{
                System.out.println("Not a valid option, either use sell, buy, or exit");
            }
        }
    }
}
