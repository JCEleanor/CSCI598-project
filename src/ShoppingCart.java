
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart which can hold products (both individual and
 * bundles).
 * It leverages the Composite pattern to treat all products uniformly.
 */
public class ShoppingCart {
    private List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        // In a real scenario, you'd probably clone the product from an inventory
        // to avoid multiple users sharing the same object instance.
        // For now, we'll just add the reference.
        items.add(product);
        System.out.println("Added to cart: " + product.getName());
    }

    public void removeProduct(Product product) {
        items.remove(product);
        System.out.println("Removed from cart: " + product.getName());
    }

    public double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("===== Shopping Cart Contents =====");
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            for (Product item : items) {
                item.display();
            }
        }
        System.out.println("==================================");
        System.out.printf("Total Price: $%.2f%n", calculateTotal());
        System.out.println("==================================");
    }

    public List<Product> getItems() {
        // less efficient but safer
        return new ArrayList<>(items);
    }

    public void reset() {
        items.clear();
    }
}
