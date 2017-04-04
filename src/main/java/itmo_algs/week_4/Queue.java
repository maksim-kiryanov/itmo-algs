package itmo_algs.week_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author maksim-kiryanov
 */
public class Queue {
    private static final String ENQUEUE_COMMAND = "+";
    private static final String DEQUEUE_COMMAND = "-";

    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {

            int commandCount = Integer.parseInt(reader.readLine());

            ArrayBasedQueue queue = new ArrayBasedQueue();
            for (int i = 0; i < commandCount; i++) {
                String[] parts = reader.readLine().split(" ");

                if (ENQUEUE_COMMAND.equals(parts[0])) {
                    queue.enqueue(Integer.parseInt(parts[1]));
                } else if (DEQUEUE_COMMAND.equals(parts[0])) {
                    writer.println(queue.dequeue());
                } else {
                    throw new RuntimeException("Unknown command [" + parts[0] + "]");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    public static class ArrayBasedQueue {
        private static final int MAX_QUEUE_SIZE = 1_000_000;

        private int[] array = new int[MAX_QUEUE_SIZE];
        private int head = -1;
        private int tail = 0;

        void enqueue(int e) {
            if (tail == head - 1) {
                throw new IllegalStateException("Queue is full");
            }

            array[tail++] = e;

            if (head == -1) {
                head = 0;
            }

            if (tail == array.length) {
                tail = 0;
            }
        }

        int dequeue() {
            if (head == -1 || head == tail) {
                throw new IllegalStateException("Queue is empty");
            }

            int e = array[head++];

            if (head == array.length) {
                head = 0;
            }
            return e;
        }
    }
}
