class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class Busqueda {
    Node root;

    // Agregar nodo al árbol
    public void add(int data) {
        root = addRecursive(root, data);
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

        return current;
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
        root = removeRecursive(root, data);
    }

    private Node removeRecursive(Node current, int data) {
        if (current == null) {
            return null;
        }

        if (data == current.data) {
            // Nodo a eliminar encontrado
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            int smallestValue = findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
            return current;
        }

        if (data < current.data) {
            current.left = removeRecursive(current.left, data);
        } else {
            current.right = removeRecursive(current.right, data);
        }

        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
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
            return null;
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

    public static void main(String[] args) {
        Busqueda tree = new Busqueda();
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
