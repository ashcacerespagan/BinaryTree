//Ashley Cáceres Pagán, 26 June, 2024, Facilitates user interaction for inputting and processing binary tree representations.

package BinaryTree;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Binary Tree Validator!");

        boolean moreTrees = true;
        while (moreTrees) {
            try {
                String input = promptUserForInput();
                BinaryTree binaryTree = new BinaryTree(input);

                System.out.println("Indented representation of the tree:");
                binaryTree.displayIndented();

                if (binaryTree.isBST()) {
                    if (binaryTree.isBalanced()) {
                        System.out.println("It is a balanced binary search tree");
                    } else {
                        System.out.println("It is a binary search tree, but it is not balanced");
                    }
                } else {
                    System.out.println("It is not a binary search tree");

                    System.out.println("In-order traversal of the original tree:");
                    List<Integer> valuesInOrder = binaryTree.getValuesInOrder();
                    System.out.println(valuesInOrder);

                    System.out.println("Height of the original tree: " + binaryTree.height());

                    // Construct a balanced binary search tree from values
                    BinaryTree balancedTree = new BinaryTree(valuesInOrder);
                    System.out.println("In-order traversal of the balanced tree:");
                    List<Integer> balancedValuesInOrder = balancedTree.getValuesInOrder();
                    System.out.println(balancedValuesInOrder);

                    System.out.println("Height of the balanced tree: " + balancedTree.height());
                }
            } catch (SyntaxException | InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
            }

            moreTrees = promptForMoreTrees();
        }

        System.out.println("Exiting Binary Tree Validator. Goodbye!");
        scanner.close();
    }

    private static String promptUserForInput() {
        System.out.print("Enter a binary tree: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new InputMismatchException("Input cannot be empty");
        }
        return input;
    }

    private static boolean promptForMoreTrees() {
        while (true) {
            System.out.print("More trees? Enter Y or N: ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
    }
}