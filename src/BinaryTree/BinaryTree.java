//Ashley Cáceres Pagán, 26 June, 2024, Manages the binary tree structure and operations.

package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private final Node root;

    public BinaryTree(String preorder) throws SyntaxException {
        if (preorder == null || preorder.trim().isEmpty()) {
            throw new SyntaxException("Invalid tree syntax: Empty input");
        }
        this.root = buildTree(preorder);
    }

    public BinaryTree(List<Integer> values) throws SyntaxException {
        if (values == null || values.isEmpty()) {
            throw new SyntaxException("List of values cannot be null or empty");
        }
        root = buildBalancedBST(values);
    }

    private Node buildBalancedBST(List<Integer> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        return buildBalancedBSTHelper(values, 0, values.size() - 1);
    }

    private Node buildBalancedBSTHelper(List<Integer> sortedValues, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node node = new Node(sortedValues.get(mid));
        node.left = buildBalancedBSTHelper(sortedValues, start, mid - 1);
        node.right = buildBalancedBSTHelper(sortedValues, mid + 1, end);
        return node;
    }

    private Node buildTree(String preorder) throws SyntaxException {
        String[] tokens = preorder.replaceAll("[()]", " $0 ").trim().split("\\s+");
        int[] index = {0};
        Node root = parseNode(tokens, index);
        if (index[0] != tokens.length) {
            throw new SyntaxException("Invalid tree syntax: Extra tokens");
        }
        return root;
    }

    private Node parseNode(String[] tokens, int[] index) throws SyntaxException {
        if (index[0] >= tokens.length) {
            throw new SyntaxException("Incomplete tree");
        }

        if (tokens[index[0]].equals("*")) {
            index[0]++;
            return null;
        }

        if (tokens[index[0]].equals("(")) {
            index[0]++;
            if (index[0] >= tokens.length) {
                throw new SyntaxException("Missing data for node");
            }

            int value;
            try {
                value = Integer.parseInt(tokens[index[0]]);
            } catch (NumberFormatException e) {
                throw new SyntaxException("Data is not an integer: " + tokens[index[0]]);
            }
            index[0]++;

            Node node = new Node(value);
            node.left = parseNode(tokens, index);
            node.right = parseNode(tokens, index);

            if (index[0] >= tokens.length || !tokens[index[0]].equals(")")) {
                throw new SyntaxException("Missing closing parenthesis");
            }
            index[0]++;

            return node;
        } else {
            throw new SyntaxException("Invalid data: " + tokens[index[0]]);
        }
    }

    public void displayIndented() {
        displayIndented(root, 0);
    }

    private void displayIndented(Node node, int level) {
        if (node != null) {
            displayIndented(node.right, level + 1);
            System.out.println(" ".repeat(level * 4) + node.data);
            displayIndented(node.left, level + 1);
        }
    }

    public boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data <= min || node.data >= max) {
            return false;
        }
        return isBST(node.left, min, node.data) && isBST(node.right, node.data, max);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    public int height() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public List<Integer> getValuesInOrder() {
        List<Integer> values = new ArrayList<>();
        inOrderTraversal(root, values);
        return values;
    }

    private void inOrderTraversal(Node node, List<Integer> values) {
        if (node != null) {
            inOrderTraversal(node.left, values);
            values.add(node.data);
            inOrderTraversal(node.right, values);
        }
    }

    private static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }
}