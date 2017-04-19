package itmo_algs.week_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author maksim-kiryanov
 */
public class RotateTree {
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

            tree.balanceUp();

            printTree(out, tree);
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    private static void printTree(PrintWriter out, Tree tree) {
        int lines = 0;

        out.println(tree.getSize());
        lines++;

        Queue<Node> queue = new LinkedList<>();
        queue.add(tree.getRoot());
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            out.print(node.key);

            if (node.getLeft(tree) != null) {
                queue.add(node.getLeft(tree));
            }
            out.print(" ");
            out.print(node.left != -1 ? lines + queue.size() : 0);

            if (node.getRight(tree) != null) {
                queue.add(node.getRight(tree));
            }
            out.print(" ");
            out.print(node.right != -1 ? lines + queue.size() : 0);

            out.println();
            lines++;
        }
    }

    private static void insert(Tree tree, int key, int leftChildIndex, int rightChildIndex) {
        int index = tree.index;
        Node node = tree.getNode(index);
        if (node == null) {
            node = new Node();
        }
        node.key = key;

        if (leftChildIndex != -1) {
            Node leftChild = tree.getNode(leftChildIndex);
            if (leftChild == null) {
                leftChild = tree.push(leftChildIndex, new Node());
            }
            leftChild.parent = index;
            node.left = leftChildIndex;
        }

        if (rightChildIndex != -1) {
            Node rightChild = tree.getNode(rightChildIndex);
            if (rightChild == null) {
                rightChild = tree.push(rightChildIndex, new Node());
            }
            rightChild.parent = index;
            node.right = rightChildIndex;
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
                node = node.getLeft(tree);
            } else {
                Node peekNode = stack.peek();
                Node rightChild = peekNode.getRight(tree);
                if (rightChild != null && lastVisitedNode != rightChild) {
                    node = rightChild;
                } else {
                    visit(tree, peekNode);
                    lastVisitedNode = stack.pop();
                }
            }
        }
    }

    private static void visit(Tree tree, Node node) {
        node.height = 1 + Math.max(getHeight(tree, node.left), getHeight(tree, node.right));
        node.balanceFactor = getHeight(tree, node.right) - getHeight(tree, node.left);
    }

    private static int getHeight(Tree tree, int nodeIndex) {
        Node node = tree.getNode(nodeIndex);
        return node != null ? node.height : 0;
    }

    static class Tree {
        private static final int MAX_TREE_SIZE = 200000;

        private Node[] nodes = new Node[MAX_TREE_SIZE];
        private int rootIndex;
        private int index;

        int getSize() {
            return index;
        }

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
            return nodes[rootIndex];
        }

        private boolean isValidRangeAccess(int index) {
            return index >= 0 && index < nodes.length;
        }

        private void checkRange(int index) {
            if (!isValidRangeAccess(index)) {
                throw new ArrayIndexOutOfBoundsException("Illegal index: " + index);
            }
        }

        void balanceUp() {
            balanceUp(getRoot());
        }

        private void balanceUp(Node node) {
            while (node != null) {
                if (node.balanceFactor == 2) {
                    if (node.getRight(this) != null && node.getRight(this).balanceFactor == -1) {
                        rotateBigLeft(node);
                    } else {
                        rotateLeft(node);
                    }
                }

                node = node.getParent(this);
            }
        }

        private void rotateLeft(Node node) {
            int rightChildIndex = node.right;
            Node rightChild = getNode(rightChildIndex);
            int nodeIndex = rightChild.parent;

            node.right = rightChild.left;
            if (rightChild.left != -1) {
                getNode(rightChild.left).parent = nodeIndex;
            }

            rightChild.left = nodeIndex;
            rightChild.parent = node.parent;
            node.parent = rightChildIndex;

            if (rightChild.parent == -1) {
                rootIndex = rightChildIndex;
            }
        }

        private void rotateBigLeft(Node node) {
            int rightChildIndex = node.right;
            Node rightChild = getNode(rightChildIndex);
            int nodeIndex = rightChild.parent;

            int rootNodeIndex = rightChild.left;
            Node rootNode = getNode(rootNodeIndex);
            rootNode.parent = node.parent;

            node.right = rootNode.left;
            Node leftNode = getNode(rootNode.left);
            if (leftNode != null) {
                leftNode.parent = nodeIndex;
            }

            rightChild.left = rootNode.right;
            Node rightNode = getNode(rootNode.right);
            if (rightNode != null) {
                rightNode.parent = rightChildIndex;
            }

            rootNode.left = nodeIndex;
            node.parent = rootNodeIndex;

            rootNode.right = rightChildIndex;
            rightChild.parent = rootNodeIndex;

            if (rootNode.parent == -1) {
                rootIndex = rootNodeIndex;
            }
        }
    }

    static class Node {
        private int key;

        private int parent = -1;
        private int left = -1;
        private int right = -1;

        private int balanceFactor;
        private int height;

        Node getParent(Tree tree) {
            return tree.getNode(parent);
        }

        Node getLeft(Tree tree) {
            return tree.getNode(left);
        }

        Node getRight(Tree tree) {
            return tree.getNode(right);
        }
    }
}
