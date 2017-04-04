package itmo_algs.week_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * @author maksim-kiryanov
 */
public class Heap {
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            int n = Integer.parseInt(reader.readLine());
            int[] heap = new int[n];

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                heap[i] = Integer.parseInt(tokenizer.nextToken());
            }

            boolean result = true;
            for (int i = 1; i <= n; i++) {
                if (2 * i <= n && !(heap[i - 1] <= heap[2 * i - 1]) ||
                        2 * i + 1 <= n && !(heap[i - 1] <= heap[2 * i])) {
                    result = false;
                    break;
                }
            }

            writer.println(result ? "YES" : "NO");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
