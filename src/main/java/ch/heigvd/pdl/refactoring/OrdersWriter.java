package ch.heigvd.pdl.refactoring;

import java.util.List;

public class OrdersWriter {
    private final List<Order> orders;
    private final StringBuilder sb = new StringBuilder();

    public OrdersWriter(List<Order> orders) {
        this.orders = orders;

    }

    public String getContents() {
        sb.append("{\"orders\": [");
        for (var order : orders) {
            writeOrder(order);
            sb.append(", ");
        }

        if (orders.size() > 0) {
            removeTrailingComa();
        }

        return sb.append("]}").toString();
    }

    private void writeOrder(Order order) {
        sb.append("{");
        writeProperty("id", order.getOrderId());
        sb.append("\"products\": [");
        for (int j = 0; j < order.getProductsCount(); j++) {
            Product product = order.getProduct(j);
            writeProduct(product);
            sb.append(", ");
        }

        if (order.getProductsCount() > 0) {
            removeTrailingComa();
        }

        sb.append("]}");
    }

    private void writeProduct(Product product) {
        sb.append("{");
        writeQuotedProperty("code", product.getCode());
        writeQuotedProperty("color", product.getColor());
        if (product.getSize() != ClothSize.NA) {
            writeQuotedProperty("size", product.getSize());
        }
        writeProperty("price", product.getPrice());
        writeQuotedProperty("currency", product.getCurrency());
        removeTrailingComa();
        sb.append("}");
    }

    private void writeQuotedTerm(Object value) {
        sb.append('"').append(value).append('"');
    }

    private void writeProperty(String key, Object value, boolean quotedValue) {
        writeQuotedTerm(key);
        sb.append(": ");
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

}