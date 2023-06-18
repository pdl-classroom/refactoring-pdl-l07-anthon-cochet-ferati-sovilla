package ch.heigvd.pdl.refactoring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Order implements JsonSerializable {
    private final List<Product> products = new ArrayList<>();
    private final int id;

    public Order(int id) {
        this.id = id;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void serialize(JsonSerializer serializer) {
        serializer.beginObject();
        serializer.writeProperty("id", id);
        serializer.writePropertyCollection("products", products);
        serializer.endObject();
    }
}
