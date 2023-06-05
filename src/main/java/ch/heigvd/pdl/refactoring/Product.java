package ch.heigvd.pdl.refactoring;

public class Product {
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

    public String getCode() {
        return code;
    }

    public ProductColor getColor() {
        return color;
    }

    public ClothSize getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}