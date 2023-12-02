package libmansys;

@FunctionalInterface
public interface AttributeGetter<T> {
    String getAttributeValue(T object);
}

