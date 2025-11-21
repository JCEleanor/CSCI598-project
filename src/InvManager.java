import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//SINGLETON
public class InvManager {
    private static InvManager instance;
    private Map<Product, Integer> inventory;

    private InvManager() {
        inventory = new HashMap<>();
    }

    public static InvManager getInstance() {
        if (instance == null) {
            instance = new InvManager();
        }
        return instance;
    }

    public void addProductToInv(Product p, int qty) {
        if (inventory.containsKey(p)) {
            System.out.println("Error: Already in the system.");
        } else {
            inventory.put(p, qty);
        }
    }

    /**
     * Removes a product from inventory.
     *
     * @param p The product to remove.
     */
    public void discontinueProduct(Product p) {
        if (inventory.containsKey(p)) {
            inventory.remove(p);
        } else {
            System.out.println("Error: this product is not in the inventory");
        }
    }
    /**
     * Increases the stock for this product.
     *
     * @param p The product to add to the stock.
     * @param amount The number of items to add to the stock.
     */
    public void restock(Product p, int amount) {
        if (amount > 0) {
            int newAmount = inventory.get(p) + amount;
            inventory.put(p, newAmount);
        }
    }

    /**
     * Decreases the stock for this product.
     *
     * @param p The product to sell.
     * @param amount The number of items sold.
     * @return true if the sale was successful, false if there was not enough stock.
     */
    public boolean sell(Product p, int amount) {
        if (amount > 0 && inventory.get(p) >= amount) {
            int newAmount = inventory.get(p) - amount;
            inventory.put(p, newAmount);
            return true;
        }
        return false;
    }

    public int getQuantity(Product p) {
        return inventory.get(p);
    }

    public void setQuantity(Product p, int quantity) {
        inventory.put(p, quantity);
    }

    public void displayAllProducts() {
        for (Product p: inventory.keySet()) {
            p.display();
            System.out.println("Quantity: " + inventory.get(p));

        }
    }

}