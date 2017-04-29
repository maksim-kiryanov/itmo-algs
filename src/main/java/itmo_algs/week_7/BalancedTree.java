package itmo_algs.week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author maksim-kiryanov
 */
public class BalancedTree {
    public static void main(String[] args) {
        try (BufferedReader in = Files.newBufferedReader(Paths.get("input.txt"));
             PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get("output.txt")))) {
            int n = Integer.parseInt(in.readLine());

            StringTokenizer tokenizer = null;
            Tree tree = new Tree();
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(in.readLine());
                int key = Integer.parseInt(tokenizer.nextToken());
                int left = Integer.parseInt(tokenizer.nextToken()) - 1;
                int right = Integer.parseInt(tokenizer.nextToken()) - 1;

                insert(tree, key, left, right);
            }

            recalculateBalance(tree);

            for (int i = 0; i < n; i++) {
                out.println(tree.getEnsureNode(i).balance);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    private static void insert(Tree tree, int key, int leftChildIndex, int rightChildIndex) {
        Node node = tree.getNode(tree.index);
        if (node == null) {
            node = new Node();
        }
        node.key = key;

        if (leftChildIndex != -1) {
            Node leftChild = tree.getNode(leftChildIndex);
            if (leftChild == null) {
                leftChild = tree.push(leftChildIndex, new Node());
            }
            leftChild.parent = node;
            node.left = leftChild;
        }

        if (rightChildIndex != -1) {
            Node rightChild = tree.getNode(rightChildIndex);
            if (rightChild == null) {
                rightChild = tree.push(rightChildIndex, new Node());
            }
            rightChild.parent = node;
            node.right = rightChild;
        }

        tree.insert(node);
    }

    private static void recalculateBalance(Tree tree) {
        LinkedList<Node> stack = new LinkedList<>();
        Node lastVisitedNode = null;
        Node node = tree.getRoot();

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node peekNode = stack.peek();
                if (peekNode.right != null && lastVisitedNode != peekNode.right) {
                    node = peekNode.right;
                } else {
                    visit(peekNode);
                    lastVisitedNode = stack.pop();
                }
            }
        }
    }

    private static void visit(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        node.balance = getHeight(node.right) - getHeight(node.left);
    }

    private static int getHeight(Node node) {
        return node != null ? node.height : 0;
    }

    static class Tree {
        private static final int MAX_TREE_SIZE = 200000;

        private Node[] nodes = new Node[MAX_TREE_SIZE];
        private int index;

        void insert(Node node) {
            checkRange(index);
            nodes[index++] = node;
        }

        Node push(int index, Node node) {
            checkRange(index);
            nodes[index] = node;
            return node;
        }

        Node getNode(int index) {
            return isValidRangeAccess(index) ? nodes[index] : null;
        }

        Node getEnsureNode(int index) {
            checkRange(index);
            return nodes[index];
        }

        Node getRoot() {
            return nodes[0];
        }

        private boolean isValidRangeAccess(int index) {
            return index >= 0 && index < nodes.length;
        }

        private void checkRange(int index) {
            if (!isValidRangeAccess(index)) {
                throw new ArrayIndexOutOfBoundsException("Illegal index: " + index);
            }
        }
    }

    static class Node {
        private int key;

        private Node parent;
        private Node left;
        private Node right;

        private int balance;
        private int height;
    }
}
