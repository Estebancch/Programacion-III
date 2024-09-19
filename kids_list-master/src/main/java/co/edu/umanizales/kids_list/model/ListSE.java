package co.edu.umanizales.kids_list.model;

import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;

    // Agregar al final
    public void add(Kid kid){
        if (existsById(kid.getId())) {
            System.out.println("El niño con el ID " + kid.getId() + " ya existe en la lista");
            return;
        }

        if (head == null) {
            head = new Node(kid);
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node(kid));
        }
        size++;
    }

    // Agregar al inicio
    public void addToStart(Kid kid){
        if (existsById(kid.getId())) {
            System.out.println("El niño con el ID " + kid.getId() + " ya existe en la lista.");
            return;
        }

        if (head == null) {
            head = new Node(kid);
        } else {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    // Agregar en una posición específica
    public void addInPosition(Kid kid, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("La posición no es válida");
        }
        if (existsById(kid.getId())) {
            System.out.println("El niño con el ID " + kid.getId() + " ya existe en la lista.");
            return;
        }

        Node newNode = new Node(kid);

        if (position == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
        }

        size++;
    }

    // Invertir la lista
    public void invert() {
        if (head == null || head.getNext() == null) {
            return;
        }

        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev;
    }

    // Borrar por ID
    public void borrarxID(String id) {
        if (head == null) {
            return;
        }

        if (head.getData().getId().equals(id)) {
            head = head.getNext();
            size--;
            return;
        }

        Node temp = head;
        while (temp.getNext() != null) {
            if (temp.getNext().getData().getId().equals(id)) {
                temp.setNext(temp.getNext().getNext());
                size--;
                return;
            }
            temp = temp.getNext();
        }
    }

    // Borrar por posición
    public void borrarxPosicion(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Posicion no valida");
        }

        if (position == 0) {
            head = head.getNext();
        } else {
            Node temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
        }

        size--;
    }

    // Intercalar niños por género
    public void intercalarxGenero() {
        if (head == null || head.getNext() == null) {
            return;
        }

        ListSE boys = new ListSE();
        ListSE girls = new ListSE();

        Node temp = head;
        while (temp != null) {
            if (temp.getData().getGender() == 'M') {
                boys.add(temp.getData());
            } else if (temp.getData().getGender() == 'F') {
                girls.add(temp.getData());
            }
            temp = temp.getNext();
        }

        head = null;
        size = 0;
        Node boy = boys.getHead();
        Node girl = girls.getHead();

        while (boy != null || girl != null) {
            if (boy != null) {
                add(boy.getData());
                boy = boy.getNext();
            }
            if (girl != null) {
                add(girl.getData());
                girl = girl.getNext();
            }
        }
    }

    // Intercalar extremos
    public void intercalarExtremos() {
        if (head == null || head.getNext() == null) {
            return;
        }

        ListSE tempList = new ListSE();
        Node start = head;
        Node end = head;

        while (end.getNext() != null) {
            end = end.getNext();
        }

        while (start != null && end != null && start != end && start.getNext() != end) {
            tempList.add(start.getData());
            tempList.add(end.getData());
            start = start.getNext();

            Node temp = head;
            while (temp.getNext() != end) {
                temp = temp.getNext();
            }
            end = temp;
        }

        if (start == end) {
            tempList.add(start.getData());
        }

        head = tempList.getHead();
        size = tempList.getSize();
    }

    // Verificar si existe un niño con un ID específico
    public boolean existsById(String id) {
        Node temp = head;
        while (temp != null) {
            if (temp.getData().getId().equals(id)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }
}
