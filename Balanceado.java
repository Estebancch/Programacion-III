class Node {
    int data;
    Node left, right;
    boolean isRed; // Indica si el nodo es rojo o negro

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.isRed = true; // Los nuevos nodos son siempre rojos
    }
}

public class Balanceado {
    private Node root;

    // Agregar nodo al árbol
    public void add(int data) {
        root = addRecursive(root, data);
        root.isRed = false; // La raíz siempre es negra
    }

    private Node addRecursive(Node current, int data) {
        if (current == null) {
            return new Node(data);
        }

        if (data < current.data) {
            current.left = addRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = addRecursive(current.right, data);
        }

        return fixUp(current); // Arreglar el árbol después de la inserción
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private boolean isRed(Node node) {
        return node != null && node.isRed;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.isRed = node.isRed;
        node.isRed = true;
        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.isRed = node.isRed;
        node.isRed = true;
        return newRoot;
    }

    private void flipColors(Node node) {
        node.isRed = !node.isRed;
        if (node.left != null) node.left.isRed = false;
        if (node.right != null) node.right.isRed = false;
    }

    // Listar nodos en el árbol (recorrido en orden)
    public void list() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    // Eliminar nodo del árbol
    public void remove(int data) {
        if (!containsNode(data)) {
            return; // Si el nodo no existe, no hacemos nada
        }
        root = removeRecursive(root, data);
        if (root != null) {
            root.isRed = false; // La raíz siempre debe ser negra
        }
    }

    private Node removeRecursive(Node current, int data) {
        if (data < current.data) {
            if (!isRed(current.left) && !isRed(current.left.left)) {
                current = moveRedLeft(current);
            }
            current.left = removeRecursive(current.left, data);
        } else {
            if (isRed(current.left)) {
                current = rotateRight(current);
            }
            if (data == current.data && (current.right == null)) {
                return null;
            }
            if (!isRed(current.right) && !isRed(current.right.left)) {
                current = moveRedRight(current);
            }
            if (data == current.data) {
                Node smallestValue = findSmallestValue(current.right);
                current.data = smallestValue.data;
                current.right = removeRecursive(current.right, smallestValue.data);
            } else {
                current.right = removeRecursive(current.right, data);
            }
        }
        return fixUp(current);
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node findSmallestValue(Node node) {
        return node.left == null ? node : findSmallestValue(node.left);
    }

    // Podar árbol (eliminar todos los nodos hoja)
    public void prune() {
        root = pruneRecursive(root);
    }

    private Node pruneRecursive(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            return null; // Eliminar nodo hoja
        }
        node.left = pruneRecursive(node.left);
        node.right = pruneRecursive(node.right);
        return node;
    }

    // Obtener nodo por nivel
    public void getNodeByLevel(int level) {
        printLevel(root, level, 1);
    }

    private void printLevel(Node node, int targetLevel, int currentLevel) {
        if (node == null) {
            return;
        }
        if (currentLevel == targetLevel) {
            System.out.print(node.data + " ");
        }
        printLevel(node.left, targetLevel, currentLevel + 1);
        printLevel(node.right, targetLevel, currentLevel + 1);
    }

    // Recorrido en preorden
    public void preorder() {
        preorderTraversal(root);
        System.out.println();
    }

    private void preorderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // Recorrido en orden
    public void inorder() {
        inorderTraversal(root);
        System.out.println();
    }

    // Recorrido en postorden
    public void postorder() {
        postorderTraversal(root);
        System.out.println();
    }

    private void postorderTraversal(Node node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Verificar si el árbol es completo
    public boolean isComplete() {
        return isCompleteTree(root, 0, countNodes(root));
    }

    private boolean isCompleteTree(Node node, int index, int numberOfNodes) {
        if (node == null) {
            return true;
        }
        if (index >= numberOfNodes) {
            return false;
        }
        return isCompleteTree(node.left, 2 * index + 1, numberOfNodes)
                && isCompleteTree(node.right, 2 * index + 2, numberOfNodes);
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // Verificar si un nodo tiene hijos
    public boolean hasChildren(int data) {
        Node node = findNode(root, data);
        return node != null && (node.left != null || node.right != null);
    }

    private Node findNode(Node current, int data) {
        if (current == null) {
            return null;
        }
        if (data == current.data) {
            return current;
        }
        return data < current.data ? findNode(current.left, data) : findNode(current.right, data);
    }

    public boolean containsNode(int data) {
        return findNode(root, data) != null;
    }

    public static void main(String[] args) {
        Balanceado tree = new Balanceado();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);

        System.out.println("Recorrido en orden:");
        tree.inorder();

        System.out.println("Recorrido en preorden:");
        tree.preorder();

        System.out.println("Recorrido en postorden:");
        tree.postorder();

        System.out.println("¿Es el árbol completo? " + tree.isComplete());

        System.out.println("¿El nodo 7 tiene hijos? " + tree.hasChildren(7));

        System.out.println("Nodos en el nivel 2:");
        tree.getNodeByLevel(2);

        tree.remove(7);
        System.out.println("Árbol después de eliminar el nodo 7:");
        tree.inorder();

        tree.prune();
        System.out.println("Árbol después de podar:");
        tree.inorder();
    }
}
