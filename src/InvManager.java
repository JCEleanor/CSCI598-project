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

    /**
     * Increases the stock for this product.
     * If product already exists in inventory, just adds quantity
     * If product doesn't exist in inventory, creates new inventory item with given qty
     *
     * @param p The product to add to the stock.
     * @param qty The number of items to add to the stock.
     */
    public void addProductToInv(Product p, int qty) {
        if (inventory.containsKey(p) && qty > 0) {
            int newAmount = inventory.get(p) + qty;
            inventory.put(p, newAmount);
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