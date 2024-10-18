// Clase para representar un nodo en el árbol
class Nodo {
    Moto data;
    Nodo izquierda, derecha;

    public Nodo(Moto moto) {
        data = moto;
        izquierda = derecha = null;
    }
}

// Clase para el árbol binario
class ArbolBinario {
    Nodo raiz;

    // 1. Añadir una moto al árbol binario
    public Nodo añadirMoto(Nodo nodo, Moto moto) {
        if (nodo == null) {
            return new Nodo(moto);
        }
        if (moto.placa < nodo.data.placa) {
            nodo.izquierda = añadirMoto(nodo.izquierda, moto);
        } else if (moto.placa > nodo.data.placa) {
            nodo.derecha = añadirMoto(nodo.derecha, moto);
        } else {
            System.out.println("Moto ya existe en el árbol");
        }
        return nodo;
    }

    // 2. Contar cuántos nodos (motos) hay en el árbol
    public int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.izquierda) + contarNodos(nodo.derecha);
    }

    // 3. Calcular la altura del árbol
    public int calcularAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzquierda = calcularAltura(nodo.izquierda);
        int alturaDerecha = calcularAltura(nodo.derecha);
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    // 4. Determinar si un nodo es hoja
    public boolean esHoja(Nodo nodo) {
        return nodo.izquierda == null && nodo.derecha == null;
    }

    // 5. Podar el árbol (eliminar todos los nodos hoja)
    public Nodo podarArbol(Nodo nodo) {
        if (nodo == null) {
            return null;
        }
        if (esHoja(nodo)) {
            return null;
        }
        nodo.izquierda = podarArbol(nodo.izquierda);
        nodo.derecha = podarArbol(nodo.derecha);
        return nodo;
    }
}

// Clase Moto para representar los datos de las motos
class Moto {
    int placa;

    public Moto(int placa) {
        this.placa = placa;
    }
}