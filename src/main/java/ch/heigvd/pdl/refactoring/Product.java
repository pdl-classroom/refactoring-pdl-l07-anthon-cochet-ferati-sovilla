package ch.heigvd.pdl.refactoring;

import java.io.Serializable;

public class Product implements JsonSerializable {
    private String code;
    private ProductColor color;
    private ClothSize size;
    private double price;
    private String currency;

    public Product(String code, ProductColor color, ClothSize size, double price, String currency) {
        this.code = code;
        this.color = color;
        this.size = size;
        this.price = price;
        this.currency = currency;
    }

    @Override
    public void serialize(JsonSerializer serializer) {
        serializer.beginObject();
        serializer.writeQuotedProperty("code", code);
        serializer.writeQuotedProperty("color", color);
        if (size != ClothSize.NA) {
            serializer.writeQuotedProperty("size", size);
        }
        serializer.writeProperty("price", price);
        serializer.writeQuotedProperty("currency", currency);
        serializer.removeTrailingComa();
        serializer.endObject();
    }
}