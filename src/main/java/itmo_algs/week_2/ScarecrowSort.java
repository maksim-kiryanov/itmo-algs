package itmo_algs.week_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author maksim-kiryanov
 */
public class ScarecrowSort {
    public static void main(String[] args) {
        int n, k;
        int[] numbers;

        try (Scanner scanner = new Scanner(new File("input.txt"))
                .useDelimiter("\\s+")) {
            n = scanner.nextInt();
            k = scanner.nextInt();
            numbers = new int[n];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = scanner.nextInt();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }

        sort(numbers, k);

        boolean result = isSortedArray(numbers);

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            writer.println(result ? "YES" : "NO");
        } catch (IOException e) {
            throw new RuntimeException("Error while write to file output.txt", e);
        }
    }

    private static void sort(int[] numbers, int k) {
        for (int i = 0; i < k; i++) {
            int len = (numbers.length - 1 - i) / k;
                qsort(numbers, i, i + k * len, k);
            if (len > 1) {
            }
        }
    }

    private static void qsort(int[] a, int left, int right, int k) {
        int len = (right - left) / k + 1;
        int key = a[left + k * (len / 2)];
        int i = left;
        int j = right;

        do {
            while (a[i] < key)
                i += k;
            while (key < a[j])
                j -= k;

            if (i <= j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i += k;
                j -= k;
            }
        } while (i <= j);

        if (left < j)
            qsort(a, left, j, k);
        if (i < right)
            qsort(a, i, right, k);
    }

    private static boolean isSortedArray(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
