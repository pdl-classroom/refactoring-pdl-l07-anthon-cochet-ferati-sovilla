package ch.heigvd.pdl.refactoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Order {
    private final List<Product> products = new ArrayList<>();
    private final int id;

    public Order(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return id;
    }
    public Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
