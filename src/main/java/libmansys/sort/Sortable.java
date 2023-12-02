package libmansys.sort;

import java.util.Comparator;
import java.util.List;

@FunctionalInterface
public interface Sortable<T> {
    void sort(List<T> list, Comparator<T> comparator);
}
