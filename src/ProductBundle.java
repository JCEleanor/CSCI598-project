import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Composite in the Composite pattern.
 * A bundle can contain other products (leaves or other bundles)
 * and is treated as a single product.
 */
public class ProductBundle extends Product {
    private List<Product> children = new ArrayList<>();
    private double discount; // e.g., 0.10 for 10% discount

    @Override
    public void setProductInfo() {
        super.setProductInfo();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bundle discount: ");
        discount = sc.nextDouble();
        sc.close();
    }

    @Override
    public void add(Product product) {
        children.add(product);
    }

    @Override
    public void remove(Product product) {
        children.remove(product);
    }

    @Override
    public List<Product> getChildren() {
        return children;
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (Product child : children) {
            total += child.getPrice();
        }
        return total * (1 - discount);
    }

        @Override

        public void display() {

            System.out.println("Bundle: " + name + " by " + brand + ", Discount: " + (discount * 100) + "%, Price: $" + getPrice());

            System.out.println("--- Bundle Contents ---");

            for (Product child : children) {

                System.out.print("  - ");

                child.display();

            }

            System.out.println("--- End Bundle ---");

        }

    @Override
    public Product clone() {
        ProductBundle clonedBundle = (ProductBundle) super.clone();
        // Deep copy of children
        clonedBundle.children = new ArrayList<>();
        for (Product child : this.children) {
            clonedBundle.add(child.clone());
        }
        return clonedBundle;
    }
}
