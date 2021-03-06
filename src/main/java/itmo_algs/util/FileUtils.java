package itmo_algs.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Util class contains methods for interactions with file system.
 *
 * @author maksim-kiryanov
 */
final class FileUtils {
    private FileUtils() {
    }

    static String readFileToString(Path filePath) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (builder.length() > 0) {
                    builder.append("\n");
                }
                builder.append(line);
            }

            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not read from file " + filePath, e);
        }
    }

    static void writeStringToFile(Path filePath, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.println(content);
        } catch (IOException e) {
            throw new RuntimeException("Could not write to file " + filePath, e);
        }
    }
}
