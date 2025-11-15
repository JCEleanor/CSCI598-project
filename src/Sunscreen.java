
import java.util.Date;
import java.util.Objects;

/**
 * A concrete implementation of a BeautyProduct.
 */
public class Sunscreen extends BeautyProduct {
    private int spf;

    public Sunscreen(String name, double price, String brand, int quantity, Date expirationDate, int spf) {
        super(name, price, brand, quantity, expirationDate);
        this.spf = spf;
    }

    public int getSpf() {
        return spf;
    }

    @Override
    public void display() {
        // Re-implement display to include SPF
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");
        System.out.println("Sunscreen: " + name + " by " + brand + ", SPF: " + spf + ", Price: $" + getPrice()
                + ", Expires: " + sdf.format(getExpirationDate()) + ", Quantity: " + quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Sunscreen sunscreen = (Sunscreen) o;
        return spf == sunscreen.spf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), spf);
    }
}
