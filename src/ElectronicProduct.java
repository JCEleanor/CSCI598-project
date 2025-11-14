
import java.util.Objects;

/**
 * Represents a Leaf in the Composite pattern.
 * This is a concrete product that can be cloned (Prototype).
 */
public class ElectronicProduct extends Product {
    private int warranty; // in months

    public ElectronicProduct(String name, double price, String brand, int quantity, int warranty) {
        super(name, price, brand, quantity);
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void display() {
        System.out.println("Electronic: " + name + " by " + brand + ", Price: $" + getPrice() + ", Warranty: " + warranty + " months, Quantity: " + quantity);
    }

    @Override
    public Product clone() {
        // Uses the parent clone and then we can add specific cloning for this class if
        // needed
        return super.clone();
    }

        @Override

        public boolean equals(Object o) {

            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            ElectronicProduct that = (ElectronicProduct) o;

            return Double.compare(that.price, price) == 0 &&

                    warranty == that.warranty &&

                    quantity == that.quantity && // Added quantity

                    Objects.equals(name, that.name) &&

                    Objects.equals(brand, that.brand);

        }

    

        @Override

        public int hashCode() {

            return Objects.hash(name, price, brand, quantity, warranty); // Added quantity

        }

    }
