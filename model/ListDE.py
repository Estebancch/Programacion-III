class ListDE:
    def __init__(self):
        self.head = None
        self.tail = None

    # Añadir un niño al final de la lista DE
    def add(self, kid):
        if self.head is None:
            self.head = kid
            self.tail = kid
        else:
            self.tail.next = kid
            kid.prev = self.tail
            self.tail = kid

    # Mostrar todos los niños en la lista DE
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
            if self.head is None:
                self.head = kid
                self.tail = kid
            else:
                kid.next = self.head
                self.head.prev = kid
                self.head = kid
        else:
            temp = self.head
            for _ in range(position - 1):
                if temp is None:
                    raise IndexError("Posición fuera de rango")
                temp = temp.next
            kid.next = temp.next
            kid.prev = temp
            if temp.next is not None:
                temp.next.prev = kid
            temp.next = kid
            if kid.next is None:
                self.tail = kid

    # Eliminar un niño por ID
    def delete_by_id(self, kid_id):
        temp = self.head
        while temp is not None and temp.id != kid_id:
            temp = temp.next
        if temp is None:
            return
        if temp.prev is not None:
            temp.prev.next = temp.next
        else:
            self.head = temp.next
        if temp.next is not None:
            temp.next.prev = temp.prev
        else:
            self.tail = temp.prev

    # Eliminar un niño por posición
    def delete_by_position(self, position):
        temp = self.head
        if position == 0:
            if self.head is None:
                return
            self.head = self.head.next
            if self.head is not None:
                self.head.prev = None
            return
        for _ in range(position):
            if temp is None:
                raise IndexError("Posición fuera de rango")
            temp = temp.next
        if temp is None:
            raise IndexError("Posición fuera de rango")
        if temp.prev is not None:
            temp.prev.next = temp.next
        if temp.next is not None:
            temp.next.prev = temp.prev

    # Invertir la lista DE
    def invert(self):
        temp = None
        current = self.head
        while current is not None:
            temp = current.prev
            current.prev = current.next
            current.next = temp
            current = current.prev
        if temp is not None:
            self.head = temp.prev

    # Intercambiar extremos en la lista DE
    def swap_extremes(self):
        if self.head is None or self.head == self.tail:
            return

        first = self.head
        last = self.tail

        # Intercambiar punteros
        if first.next == last:  # Si solo hay dos nodos
            first.next = None
            last.prev = None
            last.next = first
            first.prev = last
            self.head = last
            self.tail = first
        else:
            second = first.next
            second_last = last.prev

            second_last.next = first
            first.prev = second_last
            first.next = None

            second.prev = last
            last.next = second
            last.prev = None

            self.head = last
            self.tail = first
