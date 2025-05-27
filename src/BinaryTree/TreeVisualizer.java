package BinaryTree;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TreeVisualizer extends Application {

    private BinaryTree tree;
    private Canvas canvas;
    private GraphicsContext gc;

    private Label inorderLabel;
    private Label preorderLabel;
    private Label postorderLabel;
    private Label levelOrderLabel;

    private final List<BinaryTree.Node> highlightPath = new ArrayList<>();
    private BinaryTree.Node foundNode = null;
    private Integer animatedInsertValue = null;
    private int animationOffset = 0;
    private Timeline animationTimeline;

    private double scale = 1.0;
    private double translateX = 0;
    private double translateY = 0;
    private double mouseAnchorX, mouseAnchorY;

    public TreeVisualizer() {
        this.tree = new BinaryTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox();

        Group canvasGroup = new Group();
        canvas = new Canvas(1600, 1200);
        gc = canvas.getGraphicsContext2D();
        drawTree();
        canvasGroup.getChildren().add(canvas);

        ScrollPane scrollPane = new ScrollPane(canvasGroup);
        scrollPane.setPrefViewportWidth(800);
        scrollPane.setPrefViewportHeight(600);

        canvas.setOnScroll((ScrollEvent event) -> {
            double zoomFactor = 1.05;
            if (event.getDeltaY() < 0) {
                scale /= zoomFactor;
            } else {
                scale *= zoomFactor;
            }
            drawTree();
        });

        canvas.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mouseAnchorX = e.getX();
                mouseAnchorY = e.getY();
            }
        });

        canvas.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                translateX += e.getX() - mouseAnchorX;
                translateY += e.getY() - mouseAnchorY;
                mouseAnchorX = e.getX();
                mouseAnchorY = e.getY();
                drawTree();
            }
        });

        HBox controlBox = new HBox(10);

        TextField insertField = new TextField();
        insertField.setPromptText("Insert value");
        Button insertButton = getButton(insertField);

        TextField deleteField = new TextField();
        deleteField.setPromptText("Delete value");
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            try {
                int value = Integer.parseInt(deleteField.getText());
                tree.delete(value);
                deleteField.clear();
                highlightPath.clear();
                foundNode = null;
                animatedInsertValue = null;
                drawTree();
                updateTraversalLabels();
            } catch (NumberFormatException ignored) {}
        });

        Button clearButton = new Button("Clear Tree");
        clearButton.setOnAction(e -> {
            tree = new BinaryTree();
            highlightPath.clear();
            foundNode = null;
            animatedInsertValue = null;
            drawTree();
            updateTraversalLabels();
        });

        TextField searchField = new TextField();
        searchField.setPromptText("Search value");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            try {
                int value = Integer.parseInt(searchField.getText());
                highlightPath.clear();
                foundNode = searchAndHighlight(tree.getRoot(), value);
                animatedInsertValue = null;
                drawTree();
            } catch (NumberFormatException ignored) {}
        });

        Button saveImageButton = new Button("Save PNG");
        saveImageButton.setOnAction(e -> saveCanvasAsPNG(primaryStage));

        controlBox.getChildren().addAll(insertField, insertButton, deleteField, deleteButton, clearButton, searchField, searchButton, saveImageButton);

        inorderLabel = new Label("Inorder: ");
        preorderLabel = new Label("Preorder: ");
        postorderLabel = new Label("Postorder: ");
        levelOrderLabel = new Label("Level Order: ");

        layout.getChildren().addAll(scrollPane, controlBox, inorderLabel, preorderLabel, postorderLabel, levelOrderLabel);

        Scene scene = new Scene(layout, 800, 750);
        primaryStage.setTitle("Binary Tree Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateTraversalLabels();
    }

    private Button getButton(TextField insertField) {
        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> {
            try {
                int value = Integer.parseInt(insertField.getText());
                tree.insert(value);
                insertField.clear();
                animatedInsertValue = value;
                highlightPath.clear();
                foundNode = null;
                startBounceAnimation();
                updateTraversalLabels();
            } catch (NumberFormatException ignored) {}
        });
        return insertButton;
    }

    private void saveCanvasAsPNG(Stage stage) {
        WritableImage image = canvas.snapshot(new SnapshotParameters(), null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Tree Snapshot");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files", "*.png"));
        fileChooser.setInitialFileName("tree.png");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void startBounceAnimation() {
        animationOffset = 0;
        if (animationTimeline != null) animationTimeline.stop();

        animationTimeline = new Timeline(
            new KeyFrame(Duration.millis(50), event -> {
                animationOffset = (animationOffset + 6) % 24;
                drawTree();
            })
        );
        animationTimeline.setCycleCount(10);
        animationTimeline.setOnFinished(e -> {
            animationOffset = 0;
            animatedInsertValue = null;
            drawTree();
        });
        animationTimeline.play();
    }

    private BinaryTree.Node searchAndHighlight(BinaryTree.Node current, int value) {
        if (current == null) return null;
        highlightPath.add(current);

        if (value == current.value) return current;
        if (value < current.value) return searchAndHighlight(current.left, value);
        return searchAndHighlight(current.right, value);
    }

    private void drawTree() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.save();
        gc.translate(translateX, translateY);
        gc.scale(scale, scale);
        drawNode(tree.getRoot(), 400, 50, 200);
        gc.restore();
    }

    private void drawNode(BinaryTree.Node node, double x, double y, double hGap) {
        if (node == null) return;

        double yOffset = 0;
        if (animatedInsertValue != null && node.value == animatedInsertValue) {
            yOffset = -10 * Math.sin(Math.toRadians(animationOffset * 15));
        }

        Color fillColor = Color.LIGHTBLUE;
        if (highlightPath.contains(node)) fillColor = Color.GOLD;
        if (node == foundNode) fillColor = Color.LIMEGREEN;

        gc.setFill(fillColor);
        gc.fillOval(x - 15, y + yOffset - 15, 30, 30);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y + yOffset - 15, 30, 30);

        gc.setFill(Color.BLACK);
        gc.setFont(new Font(12));
        gc.fillText(String.valueOf(node.value), x - 5, y + yOffset + 5);

        if (node.left != null) {
            gc.strokeLine(x, y + yOffset, x - hGap, y + 60);
            drawNode(node.left, x - hGap, y + 60, hGap / 2);
        }

        if (node.right != null) {
            gc.strokeLine(x, y + yOffset, x + hGap, y + 60);
            drawNode(node.right, x + hGap, y + 60, hGap / 2);
        }
    }

    private void updateTraversalLabels() {
        inorderLabel.setText("Inorder: " + tree.inOrder());
        preorderLabel.setText("Preorder: " + tree.preOrder());
        postorderLabel.setText("Postorder: " + tree.postOrder());
        levelOrderLabel.setText("Level Order: " + tree.levelOrder());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
