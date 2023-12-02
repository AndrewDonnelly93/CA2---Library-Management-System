package libmansys.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort<T> implements Sortable<T> {

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        mergeSort(list, comparator, 0, list.size() - 1);
    }

    private static <T> void mergeSort(List<T> list, Comparator<T> comparator, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(list, comparator, l, m);
            mergeSort(list, comparator, m + 1, r);
            merge(list, comparator, l, m, r);
        }
    }

    private static <T> void merge(List<T> list, Comparator<T> comparator, int l, int m, int r) {
        List<T> leftArray = new ArrayList<>(list.subList(l, m + 1));
        List<T> rightArray = new ArrayList<>(list.subList(m + 1, r + 1));

        int i = 0, j = 0, k = l;
        while (i < leftArray.size() && j < rightArray.size()) {
            if (comparator.compare(leftArray.get(i), rightArray.get(j)) <= 0) {
                list.set(k, leftArray.get(i++));
            } else {
                list.set(k, rightArray.get(j++));
            }
            k++;
        }

        while (i < leftArray.size()) {
            list.set(k++, leftArray.get(i++));
        }
        while (j < rightArray.size()) {
            list.set(k++, rightArray.get(j++));
        }
    }
}

