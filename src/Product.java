
import java.util.List;

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
    protected int inStock;

    public Product(String name, double price, String brand, int inStock) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.inStock = inStock;
    }

    // --- Common methods for all products ---
    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int stock) {
        this.inStock = stock;
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

    // --- Composite Pattern methods ---
    // Default implementations for leaf nodes. Composites will override these.
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
