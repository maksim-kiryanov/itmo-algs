package itmo_algs.week_2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Program calculates count of inversions at input array.
 *
 * @author maksim-kiryanov
 */
public class InversionCounter {
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

        long count = countInversions(numbers);

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            writer.println(count);
        } catch (IOException e) {
            throw new RuntimeException("Error while write to file output.txt", e);
        }
    }

    private static long countInversions(int[] numbers) {
        int len = numbers.length;
        int[] buf = new int[len];

        int chunkSize = 1;
        long inversions = 0;
        while (chunkSize < len) {
            int[] temp = numbers;
            inversions += mergeChunks(numbers, buf, chunkSize);
            numbers = buf;
            buf = temp;

            chunkSize *= 2;
        }

        return inversions;
    }

    private static long mergeChunks(int[] src, int[] dest, int chunkSize) {
        int len = src.length;
        long inversions = 0;
        for (int shift = 0; shift < len; shift += chunkSize * 2) {
            int end = shift + chunkSize * 2 - 1;
            end = end < len ? end : len - 1;
            inversions += mergeChunk(src, dest, shift, end, chunkSize);
        }

        return inversions;
    }

    private static long mergeChunk(int[] src, int[] dest, int start, int end, int chunkSize) {
        int len = (end - start) + 1;
        if (len <= chunkSize) {
            System.arraycopy(src, start, dest, start, end + 1 - start);
            return 0;
        }

        long inversions = 0;
        int middle = start + chunkSize;
        for (int i = start, j = middle, k = start; i < middle && i <= end || j <= end; ) {
            if (j > end || (i < middle && src[i] <= src[j])) {
                dest[k++] = src[i++];
            } else {
                dest[k++] = src[j++];
                // rest count at left chunk
                inversions += (middle - i);
            }
        }

        return inversions;
    }
}
