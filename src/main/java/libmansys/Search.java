package libmansys;

import java.lang.reflect.Field;
import java.util.List;

public class Search
{
    public Search() {
    }

    public static <Object> Object LinearSearchByStringAttribute(List<Object> list, String value, String attributeName) throws NoSuchFieldException, IllegalAccessException {
    for (Object o : list) {
        Field privateField = o.getClass().getDeclaredField(attributeName);
        privateField.setAccessible(true);
        String attributeValue = (String) privateField.get(o);
        if (attributeValue.equals(value))
            return o;
    }
        return null;
    }

}
