
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents an abstract electronic product.
 * This class serves as a base for more specific electronic products like phones
 * or laptops.
 * It is a "Leaf" in the Composite pattern.
 */
public class ElectronicProduct extends Product {
    private int warranty; // in months

    @Override
    public void setProductInfo() {
        super.setProductInfo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product warranty: ");
        warranty = sc.nextInt();
        sc.close();
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
        System.out.println("Electronic: " + name + " by " + brand + ", Price: $" + getPrice() + ", Warranty: "
                + warranty + " months");
    }

    @Override
    public Product clone() {
        // Uses the parent clone and then we can add specific cloning for this class if
        // needed
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ElectronicProduct that = (ElectronicProduct) o;

        return Double.compare(that.price, price) == 0 &&

                warranty == that.warranty &&

                Objects.equals(name, that.name) &&

                Objects.equals(brand, that.brand);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, brand, warranty); // Added quantity
    }

}
