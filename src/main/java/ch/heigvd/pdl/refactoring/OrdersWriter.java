package ch.heigvd.pdl.refactoring;

import java.util.List;

public class OrdersWriter {
    private final List<Order> orders;

    public OrdersWriter(List<Order> orders) {
        this.orders = orders;

    }
    public String getContents() {
        var serial = new JsonSerializer();
        serial.beginObject();
        serial.writePropertyCollection("orders", orders);
        serial.endObject();
        return serial.toString();
    }
}