
import java.util.Date;
import java.util.Objects;
import java.text.SimpleDateFormat;

/**
 * Represents an abstract beauty product.
 * This class serves as a base for more specific beauty products like sunscreen
 * or shampoo.
 * It is a "Leaf" in the Composite pattern.
 */
public abstract class BeautyProduct extends Product {
    private Date expirationDate;

    public BeautyProduct(String name, double price, String brand, int quantity, Date expirationDate) {
        super(name, price, brand, quantity);
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void display() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("Beauty: " + name + " by " + brand + ", Price: $" + getPrice() + ", Expires: "
                + sdf.format(expirationDate) + ", Quantity: " + quantity);
    }

    @Override
    public Product clone() {
        BeautyProduct cloned = (BeautyProduct) super.clone(); // shallow copy
        // Date is mutable, so we should create a new Date object for the clone
        cloned.expirationDate = (Date) this.expirationDate.clone();
        return cloned;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BeautyProduct that = (BeautyProduct) o;

        return Double.compare(that.price, price) == 0 &&

                quantity == that.quantity && // Added quantity

                Objects.equals(name, that.name) &&

                Objects.equals(brand, that.brand) &&

                Objects.equals(expirationDate, that.expirationDate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, brand, quantity, expirationDate); // Added quantity
    }

}
