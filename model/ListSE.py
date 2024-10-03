class ListSE:
    def __init__(self):
        self.head = None

    # Añadir un niño al final de la lista SE
    def add(self, kid):
        if self.head is None:
            self.head = kid
        else:
            temp = self.head
            while temp.next is not None:
                temp = temp.next
            temp.next = kid

    # Mostrar todos los niños en la lista SE
    def show_kids(self):
        kids = []
        temp = self.head
        while temp is not None:
            kids.append(temp)
            temp = temp.next
        return kids

    # Añadir un niño en una posición específica
    def add_in_position(self, kid, position):
        if position == 0:
            kid.next = self.head
            self.head = kid
        else:
            temp = self.head
            for _ in range(position - 1):
                if temp is None:
                    raise IndexError("Posición fuera de rango")
                temp = temp.next
            kid.next = temp.next
            temp.next = kid

    # Eliminar un niño por ID
    def delete_by_id(self, kid_id):
        temp = self.head
        if temp is not None and temp.id == kid_id:
            self.head = temp.next
            return
        prev = None
        while temp is not None and temp.id != kid_id:
            prev = temp
            temp = temp.next
        if temp is None:
            return
        prev.next = temp.next

    # Eliminar un niño por posición
    def delete_by_position(self, position):
        temp = self.head
        if position == 0:
            self.head = temp.next
            return
        for _ in range(position - 1):
            if temp is None:
                raise IndexError("Posición fuera de rango")
            temp = temp.next
        if temp is None or temp.next is None:
            raise IndexError("Posición fuera de rango")
        temp.next = temp.next.next

    # Invertir la lista SE
    def invert(self):
        prev = None
        current = self.head
        while current is not None:
            next_node = current.next
            current.next = prev
            prev = current
            current = next_node
        self.head = prev

    # Intercambiar extremos de la lista SE
    def swap_extremes(self):
        if self.head is None or self.head.next is None:
            return

        first = self.head
        second_last = None
        last = self.head

        # Encontrar el último nodo y el penúltimo nodo
        while last.next is not None:
            second_last = last
            last = last.next

        if second_last is not None:
            second_last.next = first
            last.next = first.next
            self.head = last
            first.next = None
