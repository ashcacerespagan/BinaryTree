package BinaryTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        boolean running = true;

        while (running) {
            System.out.println("\nBinary Tree Menu:");
            System.out.println("1. Insert Node");
            System.out.println("2. Delete Node");
            System.out.println("3. Check if Tree Contains Value");
            System.out.println("4. Print Inorder Traversal");
            System.out.println("5. Print Preorder Traversal");
            System.out.println("6. Print Postorder Traversal");
            System.out.println("7. Print Level Order Traversal");
            System.out.println("8. Count Nodes");
            System.out.println("9. Count Leaves");
            System.out.println("10. Get Height");
            System.out.println("11. Check if Tree is Balanced");
            System.out.println("12. Check if Tree is Symmetric");
            System.out.println("13. Check if Tree is Complete");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int insertVal = scanner.nextInt();
                    tree.insert(insertVal);
                    break;
                case 2:
                    System.out.print("Enter value to delete: ");
                    int deleteVal = scanner.nextInt();
                    tree.delete(deleteVal);
                    break;
                case 3:
                    System.out.print("Enter value to search: ");
                    int searchVal = scanner.nextInt();
                    System.out.println(tree.contains(searchVal) ? "Value exists." : "Value not found.");
                    break;
                case 4:
                    System.out.println("Inorder: " + tree.inOrder());
                    break;
                case 5:
                    System.out.println("Preorder: " + tree.preOrder());
                    break;
                case 6:
                    System.out.println("Postorder: " + tree.postOrder());
                    break;
                case 7:
                    System.out.println("Level Order: " + tree.levelOrder());
                    break;
                case 8:
                    System.out.println("Total Nodes: " + tree.countNodes());
                    break;
                case 9:
                    System.out.println("Leaf Nodes: " + tree.countLeaves());
                    break;
                case 10:
                    System.out.println("Tree Height: " + tree.getHeight());
                    break;
                case 11:
                    System.out.println(tree.isBalanced() ? "Tree is balanced." : "Tree is not balanced.");
                    break;
                case 12:
                    System.out.println(tree.isSymmetric() ? "Tree is symmetric." : "Tree is not symmetric.");
                    break;
                case 13:
                    System.out.println(tree.isComplete() ? "Tree is complete." : "Tree is not complete.");
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Program exited.");
    }
}
