package BinaryTree;

import java.util.ArrayList;
import java.util.List;

import java.util.Queue;
import java.util.LinkedList;


public class BinaryTree {
    private Node root;

    public Node getRoot() {
    return root;
}

    public static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    // Insert node
    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) return new Node(value);
        if (value < current.value) current.left = insertRecursive(current.left, value);
        else if (value > current.value) current.right = insertRecursive(current.right, value);
        return current;
    }

    // Check if value exists
    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node current, int value) {
        if (current == null) return false;
        if (value == current.value) return true;
        return value < current.value ? containsRecursive(current.left, value) : containsRecursive(current.right, value);
    }

    // Delete node
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) return null;

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = deleteRecursive(current.right, value);
        } else {
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;
            int smallest = findSmallest(current.right);
            current.value = smallest;
            current.right = deleteRecursive(current.right, smallest);
        }
        return current;
    }

    private int findSmallest(Node root) {
        return root.left == null ? root.value : findSmallest(root.left);
    }

    // Traversals
    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(Node node, List<Integer> list) {
        if (node != null) {
            inOrderRecursive(node.left, list);
            list.add(node.value);
            inOrderRecursive(node.right, list);
        }
    }

    public List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrderRecursive(root, result);
        return result;
    }

    private void preOrderRecursive(Node node, List<Integer> list) {
        if (node != null) {
            list.add(node.value);
            preOrderRecursive(node.left, list);
            preOrderRecursive(node.right, list);
        }
    }

    public List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrderRecursive(root, result);
        return result;
    }

    private void postOrderRecursive(Node node, List<Integer> list) {
        if (node != null) {
            postOrderRecursive(node.left, list);
            postOrderRecursive(node.right, list);
            list.add(node.value);
        }
    }

    public List<Integer> levelOrder() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            result.add(node.value);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }

    // Tree metrics
    public int countNodes() {
        return countNodesRecursive(root);
    }

    private int countNodesRecursive(Node node) {
        if (node == null) return 0;
        return 1 + countNodesRecursive(node.left) + countNodesRecursive(node.right);
    }

    public int countLeaves() {
        return countLeavesRecursive(root);
    }

    private int countLeavesRecursive(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRecursive(node.left) + countLeavesRecursive(node.right);
    }

    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeightRecursive(node.left), getHeightRecursive(node.right));
    }

    // Tree property checks
    public boolean isBalanced() {
        return isBalancedRecursive(root) != -1;
    }

    private int isBalancedRecursive(Node node) {
        if (node == null) return 0;
        int left = isBalancedRecursive(node.left);
        int right = isBalancedRecursive(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }

    public boolean isSymmetric() {
        return isMirror(root, root);
    }

    private boolean isMirror(Node a, Node b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return (a.value == b.value) && isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    public boolean isComplete() {
        if (root == null) return true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean seenNull = false;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                seenNull = true;
            } else {
                if (seenNull) return false;
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return true;
    }
}
