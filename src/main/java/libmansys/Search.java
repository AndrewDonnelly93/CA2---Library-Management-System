package libmansys;

import java.lang.reflect.Field;
import java.util.List;

public class Search {
    public Search() {
    }

    public static <T> T linearSearchByStringAttribute(List<T> list, String value, String attributeName) throws NoSuchFieldException, IllegalAccessException {
        for (T o : list) {
            Class<?> clazz = o.getClass();
            while (clazz != null) {
                try {
                    Field privateField = clazz.getDeclaredField(attributeName);
                    privateField.setAccessible(true);
                    String attributeValue = (String) privateField.get(o);
                    if (attributeValue.equals(value))
                        return o;
                    break;
                } catch (NoSuchFieldException e){
                    clazz = clazz.getSuperclass();
                }
            }
        }
        return null;
    }
}
