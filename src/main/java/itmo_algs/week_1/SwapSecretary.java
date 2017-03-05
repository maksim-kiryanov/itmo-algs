package itmo_algs.week_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Program reads from file "input.txt" n (1 <= n <= 5000) and sequence of integers and
 * sorts them and writes to log all needed swaps.
 * Then program writes log records to file "output.txt", ends with "No more swaps needed." and
 * sorted array.
 *
 * @author maksim-kiryanov
 */
public class SwapSecretary {
    private static final String SWAP_LOG_FORMAT_STRING = "Swap elements at indices %d and %d.";
    private static final String END_OF_LOGS = "No more swaps needed.";

    public static void main(String[] args) {
        int n;
        int[] a;

        try (Scanner scanner = new Scanner(new File("input.txt"))
                .useDelimiter("\\s+")) {
            n = scanner.nextInt();
            a = new int[n];
            for (int i = 0; i < a.length; i++) {
                a[i] = scanner.nextInt();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }

        List<SwapLog> logs = sort(a);

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            for (SwapLog log : logs) {
                writer.println(String.format(SWAP_LOG_FORMAT_STRING, log.leftIndex, log.rightIndex));
            }
            writer.println(END_OF_LOGS);

            println(writer, a);
        } catch (IOException e) {
            throw new RuntimeException("Error while write to file output.txt", e);
        }
    }

    private static void println(PrintWriter writer, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i != 0) writer.print(' ');
            writer.print(a[i]);
        }
        writer.println();
    }

    private static List<SwapLog> sort(int[] a) {
        List<SwapLog> logs = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            int maxIndex = 0;
            int max = a[maxIndex];

            for (int j = maxIndex + 1; j < a.length - i; j++) {
                if (a[j] >= max) {
                    maxIndex = j;
                    max = a[maxIndex];
                }
            }

            if (maxIndex < a.length - 1 - i) {
                logs.add(swap(a, maxIndex, a.length - 1 - i));
            }
        }
        return logs;
    }

    private static SwapLog swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        return new SwapLog(i, j);
    }

    private static final class SwapLog {
        private int leftIndex;
        private int rightIndex;

        SwapLog(int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex + 1;
            this.rightIndex = rightIndex + 1;
        }
    }
}
