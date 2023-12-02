package libmansys;

import java.util.List;

public class Search {

        public static <T> T linearSearchByAttribute(List<T> list, AttributeGetter<T> getter, String value) {
            for (T o : list) {
                String attributeValue = getter.getAttributeValue(o);
                if (attributeValue != null && attributeValue.equals(value)) {
                    return o;
                }
            }
            return null;
        }
    }


