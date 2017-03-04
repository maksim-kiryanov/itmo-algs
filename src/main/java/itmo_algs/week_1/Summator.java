package itmo_algs.week_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Program reads two numbers a, b (-10ˆ9 <= a <= 10^9, -10ˆ9 <= b <= 10^9)
 * from file "input.txt", sums them and writes result to file "output.txt".
 *
 * @author maksim-kiryanov
 */
public class Summator {

    public static void main(String[] args) {
        int a;
        int b;

        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            scanner.useDelimiter("\\s+");
            a = scanner.nextInt();
            b = scanner.nextInt();
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }

        int result = a + b;

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.println(result);
        } catch (IOException e) {
            throw new RuntimeException("Error while write to file output.txt", e);
        }
    }
}
