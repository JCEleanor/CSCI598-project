
import java.util.Date;
import java.util.Objects;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Represents an abstract beauty product.
 * This class serves as a base for more specific beauty products like sunscreen
 * or shampoo.
 * It is a "Leaf" in the Composite pattern.
 */
public class PerishableProduct extends Product {
    private Date expirationDate;

    @Override
    public void setProductInfo() {
        super.setProductInfo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter expiration year: ");
        int year = sc.nextInt();
        System.out.print("Enter expiration month: ");
        int month = sc.nextInt();
        System.out.print("Enter expiration day: ");
        int day = sc.nextInt();

        expirationDate = new Date(year-1900, month, day);
        sc.close();
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
                + sdf.format(expirationDate));
    }

    @Override
    public Product clone() {
        PerishableProduct cloned = (PerishableProduct) super.clone(); // shallow copy
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

        PerishableProduct that = (PerishableProduct) o;

        return Double.compare(that.price, price) == 0 &&

                Objects.equals(name, that.name) &&

                Objects.equals(brand, that.brand) &&

                Objects.equals(expirationDate, that.expirationDate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, brand, expirationDate); // Added quantity
    }

}
