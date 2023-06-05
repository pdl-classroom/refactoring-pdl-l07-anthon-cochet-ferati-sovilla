package ch.heigvd.pdl.refactoring;

public enum ProductColor {
    BLUE, RED, YELLOW;

    public String toString() {
        return this.name().toLowerCase();
    }
}
