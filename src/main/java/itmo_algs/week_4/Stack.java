package itmo_algs.week_4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static itmo_algs.week_4.Stack.Command.Type.POP;
import static itmo_algs.week_4.Stack.Command.Type.PUSH;

/**
 * @author maksim-kiryanov
 */
public class Stack {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("input.txt")).useDelimiter("\\s+");
             BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            int commandCount = scanner.nextInt();

            ArrayBasedStack stack = new ArrayBasedStack();
            for (int i = 0; i < commandCount; i++) {
                Command command = nextCommand(scanner);
                if (command.type == PUSH) {
                    stack.push(command.element);
                } else {
                    writer.println(stack.pop());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    private static Command nextCommand(Scanner scanner) {
        return new Command(scanner.nextLine());
    }

    static class Command {
        enum Type {
            PUSH, POP
        }

        Type type;
        int element;

        Command(String commandString) {
            String[] parts = commandString.split(" ");
            if (parts.length > 1) {
                type = PUSH;
                element = Integer.parseInt(parts[1]);
            } else {
                type = POP;
            }
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
