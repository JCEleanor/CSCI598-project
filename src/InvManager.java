import java.util.ArrayList;

public class InvManager {
    private static InvManager instance;
    private ArrayList<Product> inventory;

    //I forget - are we doing singleton here or not?
    private InvManager() {
        inventory = new ArrayList<Product>();
    }

    public static InvManager getInstance() {
        if (instance == null) {
            instance = new InvManager();
        }
        return instance;
    }

    public void addProductToInv(Product p) {
        if (inventory.contains(p)) {
            System.out.println("Error: Already in the system.");
        } else {
            inventory.add(p);
        }
    }

    public void discontinueProduct(Product p) {
        if (inventory.contains(p)) {
            inventory.remove(p);
        } else {
            System.out.println("Error: this product is not in the inventory");
        }
    }

    public void displayAllProducts() {
        for (Product p: inventory) {
            p.display();

        }
    }

}