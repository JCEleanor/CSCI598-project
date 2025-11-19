
import java.util.Objects;

/**
 * A concrete implementation of an ElectronicProduct.
 */
public class Phone extends ElectronicProduct {
    private double screenSize; // in inches

    public Phone(String name, double price, String brand, int warranty, double screenSize) {
        super(name, price, brand, warranty);
        this.screenSize = screenSize;
    }

    public double getScreenSize() {
        return screenSize;
    }

    @Override
    public void display() {
        System.out.println("Phone: " + name + " by " + brand + ", Screen: " + screenSize + "\", Price: $" + getPrice()
                + ", Warranty: " + getWarranty() + " months");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false; // Check parent fields
        Phone phone = (Phone) o;
        return Double.compare(phone.screenSize, screenSize) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), screenSize);
    }
}
