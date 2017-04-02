package itmo_algs.week_2;

import java.util.*;

/**
 * @author maksim-kiryanov
 */
public class QuickSort {
    public static void main(String[] args) {
        int[][] invariants = {
                {1, 2, 3, 4},
                {1, 2, 4, 3},
                {1, 3, 2, 4},
                {1, 3, 4, 2},
                {1, 4, 2, 3},
                {1, 4, 3, 2},

                {2, 1, 3, 4},
                {2, 1, 4, 3},
                {2, 3, 1, 4},
                {2, 3, 4, 1},
                {2, 4, 1, 3},
                {2, 4, 3, 1},

                {3, 1, 2, 4},
                {3, 1, 4, 2},
                {3, 2, 1, 4},
                {3, 2, 4, 1},
                {3, 4, 1, 2},
                {3, 4, 2, 1},

                {4, 1, 2, 3},
                {4, 1, 3, 2},
                {4, 2, 1, 3},
                {4, 2, 3, 1},
                {4, 3, 1, 2},
                {4, 3, 2, 1}
        };

        printComparisons(invariants);
    }

    private static void printComparisons(int[][] invariants) {
        Map<int[], Integer> comparisonMap = new HashMap<>();

        for (int[] a : invariants) {
            comparisonMap.put(a, qsort(Arrays.copyOf(a, a.length), 0, a.length - 1));
        }

        List<Map.Entry<int[], Integer>> comparisonEntryList = new ArrayList<>(comparisonMap.entrySet());
        comparisonEntryList.sort(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<int[], Integer> comparisonResult : comparisonEntryList) {
            int[] a = comparisonResult.getKey();
            System.out.print("[");
            for (int j = 0; j < a.length; j++) {
                if (j > 0) System.out.print(" ");
                System.out.print(a[j]);
            }
            System.out.print("]");
            System.out.println(" comparisons: " + comparisonResult.getValue());
        }
    }

    private static int qsort(int[] a, int left, int right) {
        int key = a[(left + right) / 2];
        int i = left;
        int j = right;

        int comparisons = 0;
        do {
            while (a[i] < key) {
                i++;
                comparisons++;
            }
            comparisons++;
            while (key < a[j]) {
                j--;
                comparisons++;
            }
            comparisons++;

            if (i <= j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        if (left < j) {
            comparisons += qsort(a, left, j);
        }
        if (i < right) {
            comparisons += qsort(a, i, right);
        }

        return comparisons;
    }
}
