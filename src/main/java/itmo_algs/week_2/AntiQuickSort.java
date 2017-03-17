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
public class AntiQuickSort {
    public static void main(String[] args) {
        int n = (int)4999950000L;
        System.out.println(n);

        try (Scanner scanner = new Scanner(new File("input.txt"))
                .useDelimiter("\\s+")) {
            n = scanner.nextInt();
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }

        int[] numbers = new int[n];
        if (n > 0) {
            numbers[0] = 1;
        }
        if (n > 1) {
            numbers[1] = 2;
        }

        for (int i = 3; i <= n; i++) {
            int index = (i - 1) / 2;

            int temp = numbers[index];
            numbers[index] = i;
            numbers[i - 1] = temp;
        }

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            println(writer, numbers);
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
}
