
import java.util.Date;
import java.util.Objects;
import java.text.SimpleDateFormat;

/**
 * Represents a Leaf in the Composite pattern.
 * This is a concrete product that can be cloned (Prototype).
 */
public class BeautyProduct extends Product {
    private Date expirationDate;

    public BeautyProduct(String name, double price, String brand, int inStock, Date expirationDate) {
        super(name, price, brand, inStock);
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
                + sdf.format(expirationDate) + ", In Stock: " + inStock);
    }

    @Override
    public Product clone() {
        BeautyProduct cloned = (BeautyProduct) super.clone();
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
                Objects.equals(name, that.name) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, brand, expirationDate);
    }
}
