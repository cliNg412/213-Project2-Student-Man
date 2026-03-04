package util;

/**
 * Bubble sort for our custom util.List.
 */
public class Sort {

    public static <E extends Comparable<E>> void sort(List<E> list) {
        if (list == null || list.size() <= 1) return;

        for (int end = list.size() - 1; end > 0; end--) {
            boolean swapped = false;
            for (int i = 0; i < end; i++) {
                E a = list.get(i);
                E b = list.get(i + 1);
                if (a != null && b != null && a.compareTo(b) > 0) {
                    list.set(i, b);
                    list.set(i + 1, a);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}