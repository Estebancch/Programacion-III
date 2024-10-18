class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None
        self.is_red = True  # Los nuevos nodos son siempre rojos

class RedBlackTree:
    def __init__(self):
        self.root = None

    # Agregar nodo al árbol
    def add(self, data):
        self.root = self._add_recursive(self.root, data)
        if self.root:
            self.root.is_red = False  # La raíz siempre es negra

    def _add_recursive(self, current, data):
        if current is None:
            return Node(data)

        if data < current.data:
            current.left = self._add_recursive(current.left, data)
        elif data > current.data:
            current.right = self._add_recursive(current.right, data)

        return self._fix_up(current)  # Arreglar el árbol después de la inserción

    def _fix_up(self, node):
        if self._is_red(node.right) and not self._is_red(node.left):
            node = self._rotate_left(node)
        if self._is_red(node.left) and self._is_red(node.left.left):
            node = self._rotate_right(node)
        if self._is_red(node.left) and self._is_red(node.right):
            self._flip_colors(node)
        return node

    def _is_red(self, node):
        return node is not None and node.is_red

    def _rotate_left(self, node):
        new_root = node.right
        node.right = new_root.left
        new_root.left = node
        new_root.is_red = node.is_red
        node.is_red = True
        return new_root

    def _rotate_right(self, node):
        new_root = node.left
        node.left = new_root.right
        new_root.right = node
        new_root.is_red = node.is_red
        node.is_red = True
        return new_root

    def _flip_colors(self, node):
        node.is_red = not node.is_red
        if node.left is not None:
            node.left.is_red = False
        if node.right is not None:
            node.right.is_red = False

    # Listar nodos en el árbol (recorrido en orden)
    def list(self):
        self._inorder_traversal(self.root)
        print()

    def _inorder_traversal(self, node):
        if node is not None:
            self._inorder_traversal(node.left)
            print(node.data, end=" ")
            self._inorder_traversal(node.right)

    # Eliminar nodo del árbol
    def remove(self, data):
        if not self.contains_node(data):
            return  # Si el nodo no existe, no hacemos nada
        self.root = self._remove_recursive(self.root, data)
        if self.root is not None:
            self.root.is_red = False  # La raíz siempre debe ser negra

    def _remove_recursive(self, current, data):
        if data < current.data:
            if not self._is_red(current.left) and not self._is_red(current.left.left):
                current = self._move_red_left(current)
            current.left = self._remove_recursive(current.left, data)
        else:
            if self._is_red(current.left):
                current = self._rotate_right(current)
            if data == current.data and (current.right is None):
                return None
            if not self._is_red(current.right) and not self._is_red(current.right.left):
                current = self._move_red_right(current)
            if data == current.data:
                smallest_value = self._find_smallest_value(current.right)
                current.data = smallest_value.data
                current.right = self._remove_recursive(current.right, smallest_value.data)
            else:
                current.right = self._remove_recursive(current.right, data)
        return self._fix_up(current)

    def _move_red_left(self, node):
        self._flip_colors(node)
        if self._is_red(node.right.left):
            node.right = self._rotate_right(node.right)
            node = self._rotate_left(node)
            self._flip_colors(node)
        return node

    def _move_red_right(self, node):
        self._flip_colors(node)
        if self._is_red(node.left.left):
            node = self._rotate_right(node)
            self._flip_colors(node)
        return node

    def _find_smallest_value(self, node):
        return node.left if node.left is None else self._find_smallest_value(node.left)

    # Podar árbol (eliminar todos los nodos hoja)
    def prune(self):
        self.root = self._prune_recursive(self.root)

    def _prune_recursive(self, node):
        if node is None:
            return None
        if node.left is None and node.right is None:
            return None  # Eliminar nodo hoja
        node.left = self._prune_recursive(node.left)
        node.right = self._prune_recursive(node.right)
        return node

    # Obtener nodo por nivel
    def get_node_by_level(self, level):
        self._print_level(self.root, level, 1)

    def _print_level(self, node, target_level, current_level):
        if node is None:
            return
        if current_level == target_level:
            print(node.data, end=" ")
        self._print_level(node.left, target_level, current_level + 1)
        self._print_level(node.right, target_level, current_level + 1)

    # Recorrido en preorden
    def preorder(self):
        self._preorder_traversal(self.root)
        print()

    def _preorder_traversal(self, node):
        if node is not None:
            print(node.data, end=" ")
            self._preorder_traversal(node.left)
            self._preorder_traversal(node.right)

    # Recorrido en postorden
    def postorder(self):
        self._postorder_traversal(self.root)
        print()

    def _postorder_traversal(self, node):
        if node is not None:
            self._postorder_traversal(node.left)
            self._postorder_traversal(node.right)
            print(node.data, end=" ")

    # Verificar si el árbol es completo
    def is_complete(self):
        return self._is_complete_tree(self.root, 0, self._count_nodes(self.root))

    def _is_complete_tree(self, node, index, number_of_nodes):
        if node is None:
            return True
        if index >= number_of_nodes:
            return False
        return (self._is_complete_tree(node.left, 2 * index + 1, number_of_nodes) and
                self._is_complete_tree(node.right, 2 * index + 2, number_of_nodes))

    def _count_nodes(self, node):
        if node is None:
            return 0
        return 1 + self._count_nodes(node.left) + self._count_nodes(node.right)

    # Verificar si un nodo tiene hijos
    def has_children(self, data):
        node = self._find_node(self.root, data)
        return node is not None and (node.left is not None or node.right is not None)

    def _find_node(self, current, data):
        if current is None:
            return None
        if data == current.data:
            return current
        return self._find_node(current.left, data) if data < current.data else self._find_node(current.right, data)

    def contains_node(self, data):
        return self._find_node(self.root, data) is not None

# Prueba del árbol rojo-negro
if __name__ == "__main__":
    tree = RedBlackTree()
    tree.add(5)
    tree.add(3)
    tree.add(7)
    tree.add(2)
    tree.add(4)
    tree.add(6)
    tree.add(8)

    print("Recorrido en orden:")
    tree.list()

    print("Recorrido en preorden:")
    tree.preorder()

    print("Recorrido en postorden:")
    tree.postorder()

    print("¿Es el árbol completo? ", tree.is_complete())

    print("¿El nodo 7 tiene hijos? ", tree.has_children(7))

    print("Nodos en el nivel 2:")
    tree.get_node_by_level(2)

    tree.remove(7)
    print("\nÁrbol después de eliminar el nodo 7:")
    tree.list()

    tree.prune()
    print("Árbol después de podar:")
    tree.list()
