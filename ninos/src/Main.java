class Node {
    String data;
    int contador;
    int altura;

    Node izquierda;
    Node derecha;

    // Constructor
    public Node(String data) {
        this.data = data;
        this.contador = 1;
        this.altura = 1;
        this.izquierda = null;
        this.derecha = null;
    }
}

class ArbolBinario {
    Node raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void add(String data) {
        raiz = addRecursivo(raiz, data);
    }

    private Node addRecursivo(Node actual, String data) {
        if (actual == null) {
            return new Node(data);
        }

        if (data.compareTo(actual.data) < 0) {
            actual.izquierda = addRecursivo(actual.izquierda, data);
        } else if (data.compareTo(actual.data) > 0) {
            actual.derecha = addRecursivo(actual.derecha, data);
        } else {
            actual.contador++;
        }

        return actual;
    }

    public int count(String data) {
        return countRecursivo(raiz, data);
    }

    private int countRecursivo(Node actual, String data) {
        if (actual == null) {
            return 0;
        }
        if (data.equals(actual.data)) {
            return actual.contador;
        }
        if (data.compareTo(actual.data) < 0) {
            return countRecursivo(actual.izquierda, data);
        } else {
            return countRecursivo(actual.derecha, data);
        }
    }

    public void prune(String data) {
        raiz = pruneRecursivo(raiz, data);
    }

    private Node pruneRecursivo(Node actual, String data) {
        if (actual == null) {
            return null;
        }

        if (data.equals(actual.data)) {
            if (actual.izquierda == null && actual.derecha == null) {
                return null;
            }

            if (actual.izquierda == null) {
                return actual.derecha;
            }
            if (actual.derecha == null) {
                return actual.izquierda;
            }

            String smallestValue = findSmallestValue(actual.derecha);
            actual.data = smallestValue;
            actual.derecha = pruneRecursivo(actual.derecha, smallestValue);
            return actual;

        }
        if (data.compareTo(actual.data) < 0) {
            actual.izquierda = pruneRecursivo(actual.izquierda, data);
        } else {
            actual.derecha = pruneRecursivo(actual.derecha, data);
        }
        return actual;
    }

    private String findSmallestValue(Node root) {
        return root.izquierda == null ? root.data : findSmallestValue(root.izquierda);
    }
}
