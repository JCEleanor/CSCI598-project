import java.util.Date;

public class Client {

    public boolean checkout(int total) {
        return true;
    }
    public static void main(String[] args) {
        System.out.println("--- Demonstrating Product Hierarchy, Prototype, and Composite Patterns ---");

        // 1. Create concrete products (Leaf nodes)
        // Phone (ElectronicProduct subclass)
        Phone iphone = new Phone("iPhone 15", 999.99, "Apple", 12, 6.1);
        Phone samsung = new Phone("Galaxy S23", 899.99, "Samsung", 12, 6.2);

        // Sunscreen (BeautyProduct subclass)
        Date expiryDate = new Date(System.currentTimeMillis() + 31536000000L); // 1 year from now
        Sunscreen neutrogena = new Sunscreen("Neutrogena SPF 50", 15.50, "Neutrogena", expiryDate, 50);
        Sunscreen supergoop = new Sunscreen("Supergoop Unseen Sunscreen", 34.00, "Supergoop", expiryDate, 40);

        // Display individual products
        System.out.println("\n--- Individual Products ---");
        iphone.display();
        neutrogena.display();

        // 2. Demonstrate Prototype Pattern (cloning)
        System.out.println("\n--- Demonstrating Prototype Pattern ---");
        Phone clonedIphone = (Phone) iphone.clone();
        clonedIphone.display();

        // 3. Create a ProductBundle (Composite node)
        System.out.println("\n--- Demonstrating Composite Pattern (Product Bundle) ---");
        ProductBundle techBundle = new ProductBundle("Premium Tech Bundle", "Multi-Brand", 0.10); // 10% discount
        techBundle.add(iphone);
        techBundle.add(samsung);
        techBundle.display();

        ProductBundle beautyEssentials = new ProductBundle("Summer Beauty Pack", "Various", 0.05); // 5% discount
        beautyEssentials.add(neutrogena);
        beautyEssentials.add(supergoop);
        beautyEssentials.display();

        // Add a bundle to another bundle (nested composite)
        ProductBundle ultimateBundle = new ProductBundle("Ultimate Lifestyle Pack", "MegaCorp", 0.15);
        ultimateBundle.add(techBundle);
        ultimateBundle.add(beautyEssentials);
        ultimateBundle.display();

        // 4. Demonstrate ShoppingCart
        System.out.println("\n--- Demonstrating Shopping Cart ---");
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(iphone); // Add an individual product
        cart.addProduct(supergoop); // Add another individual product
        cart.addProduct(techBundle); // Add a bundle
        cart.addProduct(ultimateBundle); // Add a nested bundle

        cart.displayCart();

        // 5. Demonstrate restock and sell methods
        System.out.println("\n--- Demonstrating Stock Management ---");
        InvManager im = InvManager.getInstance();
        im.addProductToInv(neutrogena, 10);
        im.addProductToInv(supergoop, 10);
        im.addProductToInv(iphone, 1);
        im.addProductToInv(samsung, 5);
        im.displayAllProducts();
        System.out.println("iPhone quantity before sell: " + im.getQuantity(iphone));
        im.sell(iphone, 1);
        System.out.println("iPhone quantity after sell: " + im.getQuantity(iphone));
        im.restock(iphone, 14);
        System.out.println("iPhone quantity after restock: " + im.getQuantity(iphone));
        System.out.println("Attempting to sell 20 iPhones (only 14 in stock): " + im.sell(iphone, 20));
        System.out.println("iPhone quantity after failed sell: " + im.getQuantity(iphone));
    }
}
