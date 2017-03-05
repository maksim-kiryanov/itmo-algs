package itmo_algs.week_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Program reads from file "input.txt" number n (odd, 3 <= n <= 9999)
 * and then reads this count n real numbers (example, 1.00, 0.01).
 * Program finds lowest, median and highest values using insertion sorting
 * and writes these initial indices to file "output.txt".
 *
 * @author maksim-kiryanov
 */
public class SortLand {
    private static final int CITIZEN_NUMBER_INDEX = 0;
    private static final int CITIZEN_INCOME_INDEX = 1;

    public static void main(String[] args) {
        int n;
        float[][] M;

        try (Scanner scanner = new Scanner(new File("input.txt"))
                .useDelimiter("\\s+")) {
            n = scanner.nextInt();
            M = new float[2][n];
            for (int i = 0; i < n; i++) {
                M[CITIZEN_NUMBER_INDEX][i] = i + 1;
                M[CITIZEN_INCOME_INDEX][i] = Float.parseFloat(scanner.next());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }

        sort(M);

        int[] result = new int[3];
        result[0] = (int)M[CITIZEN_NUMBER_INDEX][0];
        result[1] = (int)M[CITIZEN_NUMBER_INDEX][(n - 1) / 2];
        result[2] = (int)M[CITIZEN_NUMBER_INDEX][n - 1];

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            println(writer, result);
        } catch (IOException e) {
            throw new RuntimeException("Error while write to file output.txt", e);
        }
    }

    private static void sort(float[][] a) {
        for (int i = 1; i < a[CITIZEN_INCOME_INDEX].length; i++) {
            int j = i - 1;
            while (j >= 0 && a[CITIZEN_INCOME_INDEX][j] > a[CITIZEN_INCOME_INDEX][j + 1]) {
                swap(a[CITIZEN_INCOME_INDEX], j, j + 1);
                swap(a[CITIZEN_NUMBER_INDEX], j, j + 1);
                j--;
            }
        }
    }

    private static void swap(float[] a, int i, int j) {
        float temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void println(PrintWriter writer, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i != 0) writer.print(' ');
            writer.print(a[i]);
        }
        writer.println();
    }
}
