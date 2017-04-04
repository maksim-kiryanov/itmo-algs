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
public class Brackets {


    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {

            int taskCount = Integer.parseInt(reader.readLine());

            for (int i = 0; i < taskCount; i++) {
                boolean result = checkBrackets(reader.readLine());
                writer.println(result ? "YES" : "NO");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    private static boolean checkBrackets(String bracketString) {
        ArrayBasedStack stack = new ArrayBasedStack();
        for (char curBracket : bracketString.toCharArray()) {
            if (!stack.isEmpty()) {
                char prevBracket = stack.pop();
                if (!isOverlapBrackets(prevBracket, curBracket)) {
                    stack.push(prevBracket);
                    stack.push(curBracket);
                }
            } else {
                stack.push(curBracket);
            }
        }

        return stack.isEmpty();
    }

    private static boolean isOverlapBrackets(char prevBracket, char curBracket) {
        return '(' == prevBracket && ')' == curBracket ||
                '[' == prevBracket && ']' == curBracket;
    }

    public static class ArrayBasedStack {
        private static final int MAX_STACK_SIZE = 1_000_000;

        private char[] array = new char[MAX_STACK_SIZE];
        private int top = -1;

        void push(char i) {
            if (top == array.length) {
                throw new IllegalStateException("Stack is full");
            }

            array[++top] = i;
        }

        char pop() {
            if (top == -1) {
                throw new IllegalStateException("Stack is empty");
            }

            return array[top--];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }
}
