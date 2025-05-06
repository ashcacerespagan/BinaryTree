//Ashley Cáceres Pagán, 26 June, 2024, Conducts unit tests to verify the functionality of the BinaryTree class.

package BinaryTreeTest;

import BinaryTree.BinaryTree;
import BinaryTree.SyntaxException;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTest {

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        List<String> testCases = List.of(
                "(53 (28 (11 * *) (41 * *)) (83 (67 * *) *))",
                "(63 (51 (20 (13 * *) *) *) *)",
                "(13 (53 * *) (11 (59 * *) *))",
                "(10 (5 (2 * *) (7 * *)) (15 (12 * *) (20 * *)))",
                "(10 (5 (2 * *) (12 * *)) (15 (7 * *) (20 * *)))",
                "(10 (5 (2 * *) (7 * *) (15 (12 * *) (20 * *))))",
                "",
                "10",
                "(10 (8 (6 (4 (2 * *) *) *) *) *)",
                "(1 * (2 * (3 * (4 * (5 * *)))))"
        );

        for (String testCase : testCases) {
            System.out.println("Running test case with input: " + testCase);
            boolean passed = false;
            try {
                BinaryTree binaryTree = new BinaryTree(testCase);

                System.out.println("Indented representation of the tree:");
                binaryTree.displayIndented();

                if (binaryTree.isBST()) {
                    if (binaryTree.isBalanced()) {
                        System.out.println("It is a balanced binary search tree");
                    } else {
                        System.out.println("It is a binary search tree, but it is not balanced");
                    }
                    passed = true;
                } else {
                    System.out.println("It is not a binary search tree");

                    System.out.println("In-order traversal of the original tree:");
                    List<Integer> valuesInOrder = binaryTree.getValuesInOrder();
                    System.out.println(valuesInOrder);

                    System.out.println("Height of the original tree: " + binaryTree.height());

                    // Construct a balanced binary search tree from values
                    BinaryTree balancedTree = new BinaryTree(new ArrayList<>(valuesInOrder));
                    System.out.println("In-order traversal of the balanced tree:");
                    List<Integer> balancedValuesInOrder = balancedTree.getValuesInOrder();
                    System.out.println(balancedValuesInOrder);

                    System.out.println("Height of the balanced tree: " + balancedTree.height());
                }
            } catch (SyntaxException e) {
                System.out.println("Caught SyntaxException: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Caught Exception: " + e.getMessage());
            }
            System.out.println("Test case completed");

            // Display pass/fail statement after test case completion
            if (passed) {
                System.out.println("Test case passed\n");
            } else {
                System.out.println("Test case failed\n");
            }
        }
    }
}