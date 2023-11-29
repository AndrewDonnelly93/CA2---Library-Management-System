package libmansys;

import java.lang.reflect.Field;
import java.util.List;

public class Search
{
    public Search() {
    }

    public static <Object> Object LinearSearchString (List<Object> list, String value, String attribute) throws NoSuchFieldException {
    for (Object o : list) {
        Class<?> type = o.getClass();
        String field = type.getDeclaredField(attribute).getName();
        if (field.equals(value))
            return o;
    }
        return null;
    }


}
