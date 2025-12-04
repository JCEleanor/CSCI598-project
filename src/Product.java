
import java.util.List;
import java.util.Scanner;

/**
 * Represents the component in the Composite pattern and the prototype in the
 * Prototype pattern.
 * This abstract class defines the common interface for both individual products
 * (leaves)
 * and product bundles (composites).
 */
public abstract class Product implements Cloneable {
    protected String name;
    protected double price;
    protected String brand;

    public void setProductInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product name: ");
        name = sc.nextLine();
        System.out.print("Enter product price: ");
        price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter product brand: ");
        brand = sc.nextLine();
    }

    // --- Common methods for all products ---
    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    /**
     * Calculates the price. For a single product, it's its own price.
     * For a bundle, it's the sum of its components' prices.
     * 
     * @return the price of the product or bundle.
     */
    public abstract double getPrice();

    /**
     * Displays the product's information.
     */
    public abstract void display();

    // --- Prototype Pattern method ---
    @Override
    public Product clone() {
        Product clone = null;
        try {
            clone = (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should not happen since we are implementing Cloneable
            e.printStackTrace();
        }
        return clone;
    }

    /**
     * 
     * `addToCart()` and `removeFromCart()`: These methods imply that a Product
     * should be aware of the ShoppingCart.
     * In a robust design, a Product should just be a data object that represents an
     * item. The ShoppingCart should be
     * responsible for managing its own contents, and another class like InvManager
     * should be responsible for tracking
     * stock changes. This avoids giving the Product class too many
     * responsibilities.
     * 
     */

    // --- Composite Pattern methods ---
    // Default implementations for leaf nodes. Composites will override these.
    // not abostract methods so client can treat all Product objects uniformly
    public void add(Product product) {
        throw new UnsupportedOperationException("This operation is not supported for a single product.");
    }

    public void remove(Product product) {
        throw new UnsupportedOperationException("This operation is not supported for a single product.");
    }

    public List<Product> getChildren() {
        throw new UnsupportedOperationException("This operation is not supported for a single product.");
    }
}
