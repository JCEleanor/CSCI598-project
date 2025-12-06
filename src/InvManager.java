import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//SINGLETON
public class InvManager {
    private static InvManager instance;
    private Map<String, ProductInv> inventory;

    private InvManager() {
        inventory = new HashMap<>();
    }

    public static InvManager getInstance() {
        if (instance == null) {
            instance = new InvManager();
        }
        return instance;
    }

    public Product getProduct(String name) throws Exception {
        if (inventory.containsKey(name)) {
            return inventory.get(name).getProduct();
        }
        System.out.println("Error: no product exists with that name");
        throw new Exception("No product of name");
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
        if (p != null) {
            String name = p.getName();
            if (inventory.containsKey(name) && inventory.get(name).getQty() > 0) {
                int newAmount = inventory.get(name).getQty() + qty;
                inventory.get(name).setQty(newAmount);
            } else {
                inventory.put(name, new ProductInv(p, qty));
            }
            System.out.println("Added " + qty + " " + name + "(s) to inventory.");
        } else {
            System.out.println("Error: product does not exist.");
        }
    }

    /**
     * Removes a product from inventory.
     *
     * @param p The product to remove.
     */
    public void discontinueProduct(Product p) {
        if (p != null) {
            String name = p.getName();
            if (inventory.containsKey(name)) {
                inventory.remove(name);
                System.out.println("Removed " + name + " from inventory.");
            } else {
                System.out.println("Error: this product is not in the inventory.");
            }
        } else {
            System.out.println("Error: product does not exist.");
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
        if (p != null) {
            String name = p.getName();
            if (inventory.containsKey(name)) {
                if (inventory.get(name).getQty() >= amount && amount > 0) {
                    int newAmount = inventory.get(name).getQty() - amount;
                    inventory.get(name).setQty(newAmount);
                    return true;
                } else {
                    System.out.println("Error: not enough stock");
                }
            } else {
                System.out.println("Error: this product is not in the inventory");
            }
        } else {
            System.out.println("Error: product does not exist.");
        }
        return false;
    }

    public int getQuantity(Product p) {
        if (p != null) {
            String name = p.getName();
            if (inventory.containsKey(name)) {
                return inventory.get(name).getQty();
            } else {
                System.out.println("Error: this product is not in the inventory");
            }
        } else {
            System.out.println("Error: product does not exist.");
        }
        return 0;
    }

    public void setQuantity(Product p, int quantity) {
        if (p != null) {
            inventory.put(p.getName(), new ProductInv(p, quantity));
        }
    }

    public void displayAllProducts() {
        for (String name: inventory.keySet()) {
            inventory.get(name).getProduct().display();
            System.out.println("Quantity: " + inventory.get(name).getQty());

        }
    }

}