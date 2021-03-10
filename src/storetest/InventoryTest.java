package storetest;

import org.junit.jupiter.api.Test;
import store.Inventory;
import store.Product;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void getProductQuantity() {
        Product p1 = new Product("book", 0, 10);
        Product p2 = new Product("coffee", 3, 500);
        HashMap<Integer, Integer> h1 = new HashMap<>();

        Product[] products = {p1, p2};
        int[] quantities = {15, 100};
        Inventory inv = new Inventory(products, quantities);

        h1.put(0, 15);
        h1.put(3, 100);

        assertEquals(h1, inv.getProductQuantity());
    }

    @Test
    void getProductInfo() {
        Product p1 = new Product("book", 0, 10);
        Product p2 = new Product("coffee", 3, 500);
        HashMap<Integer, Product> h2 = new HashMap<>();

        Product[] products = {p1, p2};
        int[] quantities = {15, 100};
        Inventory inv = new Inventory(products, quantities);

        h2.put(0, p1);
        h2.put(3, p2);

        assertEquals(h2, inv.getProductInfo());
    }

    @Test
    void getStock() {
        Product p1 = new Product("book", 0, 10);
        Product p2 = new Product("coffee", 3, 500);
        Product[] products = {p1, p2};
        int[] quantities = {15, 100};
        Inventory inv = new Inventory(products, quantities);

        assertEquals(15, inv.getStock(0));
        assertEquals(100, inv.getStock(3));
        assertEquals(-1, inv.getStock(2));
    }

    @Test
    void addStock() {
        Inventory inv = new Inventory();
        Product p1 = new Product("book", 0, 10);
        Product p2 = new Product("coffee", 3, 500);
        inv.addStock(p1, 15);
        inv.addStock(p2, 100);

        assertEquals(15, inv.getStock(0));
        assertEquals(100, inv.getStock(3));
        assertEquals(-1, inv.getStock(2));
    }

    @Test
    void removeStock() {
        Product p1 = new Product("book", 0, 10);
        Product p2 = new Product("coffee", 3, 500);
        Product[] products = {p1, p2};
        int[] quantities = {15, 100};
        Inventory inv = new Inventory(products, quantities);

        inv.removeStock(0, 3, false);
        assertEquals(12, inv.getStock(0));

        inv.removeStock(0, 3, true);
        assertEquals(9, inv.getStock(0));

        inv.removeStock(0, 9, true);
        assertEquals(-1, inv.getStock(0));

        inv.removeStock(3, 999, false);
        assertEquals(0, inv.getStock(3));
    }

    @Test
    void removeProduct() {
        Product p1 = new Product("book", 0, 10);
        Product p2 = new Product("coffee", 3, 500);
        Product[] products = {p1, p2};
        int[] quantities = {15, 100};
        Inventory inv = new Inventory(products, quantities);

        inv.removeProduct(-42);

        inv.removeProduct(0);
        assertEquals(-1, inv.getStock(0));
    }

    @Test
    void getInfo() {
        Product p1 = new Product("book", 0, 10);
        Product p2 = new Product("coffee", 3, 500);
        Product[] products = {p1, p2};
        int[] quantities = {15, 100};
        Inventory inv = new Inventory(products, quantities);

        assertNull(inv.getInfo(-42));
        assertEquals(p1, inv.getInfo(0));
    }
}