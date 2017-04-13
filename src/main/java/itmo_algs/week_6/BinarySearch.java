package itmo_algs.week_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * @author maksim-kiryanov
 */
public class BinarySearch {
    public static void main(String[] args) {
        try (BufferedReader in = Files.newBufferedReader(Paths.get("input.txt"));
             PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get("output.txt")))) {
            int n = Integer.parseInt(in.readLine());

            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(tokenizer.nextToken());
            }

            int m = Integer.parseInt(in.readLine());
            tokenizer = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                int key = Integer.parseInt(tokenizer.nextToken());
                solve(array, key, out);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    public static void solve(int[] array, int key, PrintWriter out) {
        int l = -1;
        int r = array.length;
        int prev_r = r;

        // search left border
        while (r > l + 1) {
            int m = (l + r) / 2;
            if (array[m] < key) {
                l = m;
            } else {
                if (array[m] > key) {
                    prev_r = r;
                }
                r = m;
            }
        }

        if (r < array.length && array[r] == key) {
            out.print((r + 1));
            out.print(" ");

            l = r;
            r = prev_r;
            // search right border
            while (r > l + 1) {
                int m = (l + r) / 2;
                if (array[m] <= key) {
                    l = m;
                } else {
                    r = m;
                }
            }
            out.println((l + 1));
        } else {
            out.print(-1);
            out.print(" ");
            out.println(-1);
        }
    }
}
