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
public class MergeSort {
    private static PrintWriter writer;

    public static void main(String[] args) {
        int n;
        long[] numbers;

        try (Scanner scanner = new Scanner(new File("input.txt"))
                .useDelimiter("\\s+")) {
            n = scanner.nextInt();
            numbers = new long[n];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = scanner.nextLong();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            MergeSort.writer = writer;
            numbers = sort(numbers);
            println(writer, numbers);
        } catch (IOException e) {
            throw new RuntimeException("Error while write to file output.txt", e);
        }
    }

    private static long[] sort(long[] numbers) {
        return mergeSort(numbers);
    }

    private static long[] mergeSort(long[] numbers) {
        int len = numbers.length;

        long[] buf = new long[len];

        int chunkSize = 1;

        while (chunkSize < len) {
            long[] temp = numbers;
            mergeChunks(numbers, buf, chunkSize);
            numbers = buf;
            buf = temp;

            chunkSize *= 2;
        }

        return numbers;
    }

    private static void mergeChunks(long[] src, long[] dest, int chunkSize) {
        int len = src.length;
        for (int shift = 0; shift < len; shift += chunkSize * 2) {
            int end = shift + chunkSize * 2 - 1;
            end = end < len ? end : len - 1;
            mergeChunk(src, dest, shift, end, chunkSize);
        }
    }

    private static void mergeChunk(long[] src, long[] dest, int start, int end, int chunkSize) {
        int len = (end - start) + 1;
        if (len <= chunkSize) {
            System.arraycopy(src, start, dest, start, end + 1 - start);
            return;
        }

        int middle = start + chunkSize;

        for (int i = start, j = middle, k = start; i < middle && i <= end || j <= end; ) {
            if (j > end || (i < middle && src[i] <= src[j])) {
                dest[k++] = src[i++];
            } else {
                dest[k++] = src[j++];
            }
        }

        writer.println(String.format("%d %d %d %d", start + 1, end + 1, dest[start], dest[end]));
    }

    private static void println(PrintWriter writer, long[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i != 0) writer.print(' ');
            writer.print(a[i]);
        }
        writer.println();
    }
}
