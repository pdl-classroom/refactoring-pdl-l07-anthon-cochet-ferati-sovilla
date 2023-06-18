package ch.heigvd.pdl.refactoring;

public enum ProductColor {
    BLUE, RED, YELLOW;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
