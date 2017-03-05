package itmo_algs.week_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Program reads array of numbers from file "input.txt", sorts them
 * and writes result and swapping indices to "output.txt".
 *
 * @author maksim-kiryanov
 */
public class InsertionSort {
    public static void main(String[] args) {
        int n;
        int[] numbers;

        try (Scanner scanner = new Scanner(new File("input.txt"))
                .useDelimiter("\\s+")) {
            n = scanner.nextInt();
            numbers = new int[n];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = scanner.nextInt();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }

        int[] indices = sort(numbers);

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            println(writer, indices);
            println(writer, numbers);
        } catch (IOException e) {
            throw new RuntimeException("Error while write to file output.txt", e);
        }
    }

    private static int[] sort(int[] numbers) {
        int[] indices = new int[numbers.length];
        indices[0] = 1;

        for (int i = 1; i < numbers.length; i++) {
            int j = i - 1;
            while (j >= 0 && numbers[j] > numbers[j + 1]) {
                int temp = numbers[j + 1];
                numbers[j + 1] = numbers[j];
                numbers[j] = temp;
                j--;
            }

            indices[i] = j + 2;
        }

        return indices;
    }

    private static void println(PrintWriter writer, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i != 0) writer.print(' ');
            writer.print(a[i]);
        }
        writer.println();
    }
}
