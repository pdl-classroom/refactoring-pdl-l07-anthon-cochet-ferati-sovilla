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
            sb.delete(sb.length() - 2, sb.length());
        }

        return sb.append("]}").toString();
    }

    private void writeOrder(Order order) {
        sb.append("{");
        writeNumberProperty("id", order.getOrderId());
        sb.append(", ");
        sb.append("\"products\": [");
        for (int j = 0; j < order.getProductsCount(); j++) {
            Product product = order.getProduct(j);
            writeProduct(product);
            sb.append(", ");
        }

        if (order.getProductsCount() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append("]}");
    }

    private void writeProduct(Product product) {
        sb.append("{");
        writeStringProperty("code", product.getCode());
        sb.append(", ");
        writeStringProperty("color", product.getColor());
        sb.append(", ");

        if (product.getSize() != ClothSize.NA) {
            writeStringProperty("size", product.getSize());
            sb.append(", ");
        }

        writeNumberProperty("price", product.getPrice());
        sb.append(", ");
        writeStringProperty("currency", product.getCurrency());
        sb.append("}");
    }

    private StringBuilder writeKey(String key) {
        sb.append("\"").append(key).append("\"").append(": ");
        return sb;
    }

    private void writeStringProperty(String key, Object value) {
        writeKey(key).append("\"").append(value).append("\"");
    }

    private void writeNumberProperty(String key, Object number) {
        writeKey(key).append(number);
    }

}