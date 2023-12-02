package libmansys.search;

@FunctionalInterface
public interface AttributeGetter<T> {
    String getAttributeValue(T object);
}

