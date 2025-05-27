# Binary Tree Visualizer

This interactive JavaFX application allows users to build, manipulate, and visualize a binary search tree in real time. It's an educational and portfolio-ready tool that supports tree operations, animated rendering, and data export.

---

## 🚀 Features
- Insert and delete nodes dynamically
- Realtime canvas-based tree visualization
- Traversal display: Inorder, Preorder, Postorder, Level Order
- Search function with path highlighting
- Node animation (bounce effect on insert)
- Zoom in/out using mouse wheel
- Pan the tree view via mouse drag
- Save current tree view as PNG image

---

## 📦 Requirements
- Java 17 or later
- JavaFX SDK (configured in module path)

---

## 🏃‍♂️ How to Run

### IntelliJ Setup:
1. Install JavaFX SDK and extract it (e.g., to your desktop)
2. Go to **Run > Edit Configurations**
3. Add VM options:
   ```
   --module-path /path/to/javafx-sdk-XX/lib --add-modules javafx.controls,javafx.fxml
   ```
4. Run `TreeVisualizer.java`

---

## 🖼️ Export Support
- **Save PNG**: Export a screenshot of your current canvas view with zoom/pan preserved

---

## 💡 Future Enhancements (Planned)
- Export traversals to `.txt`
- Export tree structure to `.json`
- Tabbed views for raw structure and metrics
- Import saved tree files

---

## 👤 Author
Ashley Caceres Pagan

Feel free to clone and explore, or use this as a base for teaching, demos, or extensions!
