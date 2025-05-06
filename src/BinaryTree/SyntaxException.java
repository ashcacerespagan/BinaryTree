//Ashley Cáceres Pagán, 26 June, 2024, SyntaxException: Handles exceptions related to invalid tree syntax during input parsing.

package BinaryTree;

public class SyntaxException extends Exception {
    public SyntaxException(String message) {
        super(message);
    }
}