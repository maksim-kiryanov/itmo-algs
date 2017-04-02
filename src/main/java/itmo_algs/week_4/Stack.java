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
public class Stack {
    private static final String PUSH_COMMAND = "+";
    private static final String POP_COMMAND = "-";

    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {

            int commandCount = Integer.parseInt(reader.readLine());

            ArrayBasedStack stack = new ArrayBasedStack();
            for (int i = 0; i < commandCount; i++) {
                String[] parts = reader.readLine().split(" ");

                if (PUSH_COMMAND.equals(parts[0])) {
                    stack.push(Integer.parseInt(parts[1]));
                } else if (POP_COMMAND.equals(parts[0])) {
                    writer.println(stack.pop());
                } else {
                    throw new RuntimeException("Unknown command [" + parts[0] + "]");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    public static class ArrayBasedStack {
        private static final int MAX_STACK_SIZE = 1_000_000;

        private int[] array = new int[MAX_STACK_SIZE];
        private int top = -1;

        void push(int i) {
            if (top == array.length) {
                throw new IllegalStateException("Stack is full");
            }

            array[++top] = i;
        }

        int pop() {
            if (top == -1) {
                throw new IllegalStateException("Stack is empty");
            }

            return array[top--];
        }
    }
}
