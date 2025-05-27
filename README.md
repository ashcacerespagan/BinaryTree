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

## 🧭 Screenshots
| Search Highlight | Zoomed Out | Long Skewed Tree | Traversal Result |
|------------------|------------|------------------|------------------|
| ![Search](./Screenshots/Screenshot%202025-05-26%20at%2010.48.08%E2%80%AFPM.png) | ![Zoom](./Screenshots/Screenshot%202025-05-26%20at%2010.48.21%E2%80%AFPM.png) | ![Long Tree](./Screenshots/Screenshot%202025-05-26%20at%2010.48.33%E2%80%AFPM.png) | ![Traversal](./Screenshots/Screenshot%202025-05-26%20at%2010.48.43%E2%80%AFPM.png) |
| Save Dialog | Exported PNG | Tree View w/ Insert | Search Result |
|----------------|--------------|---------------------|----------------|
| ![Save](./Screenshots/Screenshot%202025-05-26%20at%2010.49.39%E2%80%AFPM.png) | ![Export](./Screenshots/Screenshot%202025-05-26%20at%2010.49.52%E2%80%AFPM.png) | ![Insert](./Screenshots/Screenshot%202025-05-26%20at%2010.50.17%E2%80%AFPM.png) | ![Found](./Screenshots/Screenshot%202025-05-26%20at%2010.50.17%E2%80%AFPM.png) |

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
