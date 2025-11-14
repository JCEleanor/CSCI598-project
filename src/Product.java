
import java.util.List;

/**
 * Represents the component in the Composite pattern and the prototype in the
 * Prototype pattern.
 * This abstract class defines the common interface for both individual products
 * (leaves)
 * and product bundles (composites).
 */
public abstract class Product implements Cloneable { // shallow copy TODO: deep copy if needed
    protected String name;
    protected double price;
    protected String brand;
    /** thinking to rename this variable to "quantity. 0 means not in stock" */
    protected int quantity;

    public Product(String name, double price, String brand, int quantity) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
    }

    // --- Common methods for all products ---
    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Increases the stock for this product.
     * 
     * @param amount The number of items to add to the stock.
     */
    public void restock(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    /**
     * Decreases the stock for this product.
     * 
     * @param amount The number of items sold.
     * @return true if the sale was successful, false if there was not enough stock.
     */
    public boolean sell(int amount) {
        if (amount > 0 && this.quantity >= amount) {
            this.quantity -= amount;
            return true;
        }
        return false;
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
