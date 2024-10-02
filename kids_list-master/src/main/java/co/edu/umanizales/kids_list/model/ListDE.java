package co.edu.umanizales.kids_list.model;

import lombok.Data;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    // Agregar al final
    public void add(Kid kid) {
        NodeDE newNode = new NodeDE(kid);
        if (head == null) {
            head = newNode;
        } else {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }
        size++;
    }

    // Agregar al inicio
    public void addToStart(Kid kid) {
        NodeDE newNode = new NodeDE(kid);
        if (head == null) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    // Eliminar por ID
    public void deleteById(String id) {
        if (head == null) {
            return;
        }
        NodeDE temp = head;
        while (temp != null) {
            if (temp.getData().getId().equals(id)) {
                if (temp == head) {
                    head = head.getNext();
                    if (head != null) {
                        head.setPrevious(null);
                    }
                } else {
                    if (temp.getPrevious() != null) {
                        temp.getPrevious().setNext(temp.getNext());
                    }
                    if (temp.getNext() != null) {
                        temp.getNext().setPrevious(temp.getPrevious());
                    }
                }
                size--;
                return;
            }
            temp = temp.getNext();
        }
    }

    // Mostrar lista
    public NodeDE showKids() {
        return head;
    }

    // Obtener el tamaño de la lista
    public int getSize() {
        return size;
    }
}
