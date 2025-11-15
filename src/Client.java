import java.util.Date;

public class Client {
    public static void main(String[] args) {
        System.out.println("--- Demonstrating Product Hierarchy, Prototype, and Composite Patterns ---");

        // 1. Create concrete products (Leaf nodes)
        // Phone (ElectronicProduct subclass)
        Phone iphone = new Phone("iPhone 15", 999.99, "Apple", 10, 12, 6.1);
        Phone samsung = new Phone("Galaxy S23", 899.99, "Samsung", 15, 12, 6.2);

        // Sunscreen (BeautyProduct subclass)
        Date expiryDate = new Date(System.currentTimeMillis() + 31536000000L); // 1 year from now
        Sunscreen neutrogena = new Sunscreen("Neutrogena SPF 50", 15.50, "Neutrogena", 20, expiryDate, 50);
        Sunscreen supergoop = new Sunscreen("Supergoop Unseen Sunscreen", 34.00, "Supergoop", 12, expiryDate, 40);

        // Display individual products
        System.out.println("\n--- Individual Products ---");
        iphone.display();
        neutrogena.display();

        // 2. Demonstrate Prototype Pattern (cloning)
        System.out.println("\n--- Demonstrating Prototype Pattern ---");
        Phone clonedIphone = (Phone) iphone.clone();
        clonedIphone.setQuantity(1); // Cloned item has its own quantity
        clonedIphone.display();
        System.out.println("Original iPhone quantity: " + iphone.getQuantity());
        System.out.println("Cloned iPhone quantity: " + clonedIphone.getQuantity());

        // 3. Create a ProductBundle (Composite node)
        System.out.println("\n--- Demonstrating Composite Pattern (Product Bundle) ---");
        ProductBundle techBundle = new ProductBundle("Premium Tech Bundle", "Multi-Brand", 5, 0.10); // 10% discount
        techBundle.add(iphone);
        techBundle.add(samsung);
        techBundle.display();

        ProductBundle beautyEssentials = new ProductBundle("Summer Beauty Pack", "Various", 8, 0.05); // 5% discount
        beautyEssentials.add(neutrogena);
        beautyEssentials.add(supergoop);
        beautyEssentials.display();

        // Add a bundle to another bundle (nested composite)
        ProductBundle ultimateBundle = new ProductBundle("Ultimate Lifestyle Pack", "MegaCorp", 2, 0.15);
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
        System.out.println("iPhone quantity before sell: " + iphone.getQuantity());
        iphone.sell(1);
        System.out.println("iPhone quantity after sell: " + iphone.getQuantity());
        iphone.restock(5);
        System.out.println("iPhone quantity after restock: " + iphone.getQuantity());
        System.out.println("Attempting to sell 20 iPhones (only 14 in stock): " + iphone.sell(20));
        System.out.println("iPhone quantity after failed sell: " + iphone.getQuantity());
    }
}
