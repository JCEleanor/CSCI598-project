import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {

    static Map<String,Product> productTypes;
    static Map<String,PaymentStrategy> paymentOptions;

    public static Product buildBundle(){
        Scanner scanner = new Scanner(System.in);
        boolean addAnotherProduct = true;
        System.out.println("The following relates to the overall bundle:");
        ProductBundle bundle = new ProductBundle();
        bundle.setProductInfo();

        System.out.println("The following relates to the products to add to bundle:");
        while(addAnotherProduct){
            System.out.println("What type of product do you want to add?");
            String type = scanner.nextLine();
            Product userProduct = productTypes.get(type).clone();
            userProduct.setProductInfo();
            bundle.add(userProduct);

            System.out.println("Do you want to add another product? y/n");
            addAnotherProduct = scanner.nextLine().equalsIgnoreCase("y");
        }

        return bundle;
    }

    public static void fillAssocMaps(){
        productTypes = new HashMap<String,Product>();
        paymentOptions = new HashMap<String,PaymentStrategy>();

        productTypes.put("Electronic",new ElectronicProduct());
        productTypes.put("Perishable",new PerishableProduct());
        productTypes.put("Phone",new Phone());
        productTypes.put("Sunscreen",new Sunscreen());
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

        System.out.print("Do you want to make a product bundle? y/n");
        String productBundleDesired = scanner.nextLine();
        boolean bundle = productBundleDesired.equalsIgnoreCase("y");

        Product userProduct;
        if(bundle){
            userProduct = buildBundle();
        }else{
            System.out.println("What type of product do you want to sell?");
            String type = scanner.nextLine();
            Product userProductType = productTypes.get(type);
            if(userProductType == null){
                System.out.println("Product type does not exist");
                return;
            }
            userProduct = userProductType.clone();
            userProduct.setProductInfo();
        }

        System.out.println("How much do you want to sell?");
        int qtnIn = scanner.nextInt();
        inventory.addProductToInv(userProduct,qtnIn);
    }

    private static void buyProduct(InvManager inventory) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here is a list of products, what do you want to buy?");
        inventory.displayAllProducts();
        String productIn = scanner.next();
        Product userProduct;
        try{
            userProduct = inventory.getProduct(productIn);
        } catch (Exception e) {
            System.out.println("That product does not exist in our inventory");
            return;
        }

        System.out.println("How much do you want to buy?");
        int qtnIn = scanner.nextInt();


        System.out.println("How do you want to pay?");
        String userOption = scanner.next();
        PaymentStrategy paymentMethod = paymentOptions.get(userOption);
        if(paymentMethod == null){
            System.out.println("We don't support thay payment method");
            return;
        }
        //Change payment methods to get information by themselves (call them here)
        PaymentProcessor payProcessor = new PaymentProcessor();
        payProcessor.setPaymentMethod(paymentMethod);
        payProcessor.populatePaymentInfo();
        payProcessor.validate();

        if (inventory.sell(userProduct, qtnIn)) {
            double productTotal = userProduct.getPrice();
            payProcessor.checkout(productTotal);
        } else {
            System.out.println("Not enough stock");
        }
    }

    public static void main(String[] args) {
        InvManager inventory = InvManager.getInstance();
        fillAssocMaps();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to E-Trading. Are you here to sell or to buy today?");

        boolean exit = false;
        while(!exit) {
            System.out.println("Type sell to sell and buy to buy.");
            System.out.println("If you would like to exit type exit");
            String userIn = scanner.next();
            switch (userIn) {
                case "sell":
                    sellProduct(inventory);
                    break;
                case "buy":
                    buyProduct(inventory);
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Not a valid option, either use sell, buy, or exit");
                    break;
            }
        }
    }
}
