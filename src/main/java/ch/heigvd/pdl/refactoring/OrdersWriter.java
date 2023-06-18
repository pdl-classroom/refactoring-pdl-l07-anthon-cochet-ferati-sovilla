package ch.heigvd.pdl.refactoring;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class OrdersWriter {
    private final List<Order> orders;
    private StringBuilder sb = new StringBuilder();

    public OrdersWriter(List<Order> orders) {
        this.orders = orders;

    }

    public String getContents() {
        beginObject();
        writePropertyCollection("orders", orders, this::writeOrder);
        endObject();

        String result = sb.toString();
        sb = new StringBuilder();
        return result;
    }

    private void writeOrder(Order order) {
        beginObject();
        writeProperty("id", order.getOrderId());
        writePropertyCollection("products", order.getProducts(), this::writeProduct);
        endObject();
    }

    private void writeProduct(Product product) {
        beginObject();
        writeQuotedProperty("code", product.getCode());
        writeQuotedProperty("color", product.getColor());
        if (product.getSize() != ClothSize.NA) {
            writeQuotedProperty("size", product.getSize());
        }
        writeProperty("price", product.getPrice());
        writeQuotedProperty("currency", product.getCurrency());
        removeTrailingComa();
        endObject();
    }

    private <T> void writePropertyCollection(String key, Collection<T> collections, Consumer<T> writer) {
        writeKey(key);
        sb.append('[');
        for (var item : collections) {
            writer.accept(item);
            sb.append(", ");
        }
        if (!collections.isEmpty()) {
            removeTrailingComa();
        }
        sb.append(']');
    }
    private void writeQuotedTerm(Object value) {
        sb.append('"').append(value).append('"');
    }

    private void writeKey(String key) {
        writeQuotedTerm(key);
        sb.append(": ");
    }

    private void writeProperty(String key, Object value, boolean quotedValue) {
        writeKey(key);
        if (quotedValue) {
            writeQuotedTerm(value);
        } else {
            sb.append(value);
        }
        sb.append(", ");
    }

    private void writeQuotedProperty(String key, Object value) {
        writeProperty(key, value, true);
    }

    private void writeProperty(String key, Object value) {
        writeProperty(key, value, false);
    }

    private void removeTrailingComa() {
        sb.delete(sb.length() - 2, sb.length());
    }

    private void beginObject() {
        sb.append("{");
    }

    private void endObject() {
        sb.append("}");
    }

}