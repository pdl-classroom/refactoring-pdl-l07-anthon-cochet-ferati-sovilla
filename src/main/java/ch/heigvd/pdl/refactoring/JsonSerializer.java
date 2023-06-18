package ch.heigvd.pdl.refactoring;

import java.util.Collection;
public class JsonSerializer {

    private final StringBuilder sb = new StringBuilder();

    public void writeQuotedProperty(String key, Object value) {
        writeProperty(key, value, true);
    }

    public void writeProperty(String key, Object value) {
        writeProperty(key, value, false);
    }

    public void removeTrailingComa() {
        sb.delete(sb.length() - 2, sb.length());
    }

    public void beginObject() {
        sb.append("{");
    }

    public void endObject() {
        sb.append("}");
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public <T> void writePropertyCollection(String key, Collection<? extends JsonSerializable> serializables) {
        writeKey(key);
        sb.append('[');
        for (var item : serializables) {
            item.serialize(this);
            sb.append(", ");
        }
        if (!serializables.isEmpty()) {
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

}
