class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None


class BinaryTree:
    def __init__(self):
        self.root = None

    # Agregar nodo al árbol
    def add(self, data):
        self.root = self.add_recursive(self.root, data)

    def add_recursive(self, current, data):
        if current is None:
            return Node(data)

        if data < current.data:
            current.left = self.add_recursive(current.left, data)
        elif data > current.data:
            current.right = self.add_recursive(current.right, data)

        return current

    # Listar nodos en el árbol (recorrido en orden)
    def list(self):
        self.inorder_traversal(self.root)
        print()

    def inorder_traversal(self, node):
        if node is not None:
            self.inorder_traversal(node.left)
            print(node.data, end=" ")
            self.inorder_traversal(node.right)

    # Eliminar nodo del árbol
    def remove(self, data):
        self.root = self.remove_recursive(self.root, data)

    def remove_recursive(self, current, data):
        if current is None:
            return None

        if data == current.data:
            # Nodo a eliminar encontrado
            if current.left is None and current.right is None:
                return None
            if current.left is None:
                return current.right
            if current.right is None:
                return current.left
            smallest_value = self.find_smallest_value(current.right)
            current.data = smallest_value
            current.right = self.remove_recursive(current.right, smallest_value)
            return current

        if data < current.data:
            current.left = self.remove_recursive(current.left, data)
        else:
            current.right = self.remove_recursive(current.right, data)

        return current

    def find_smallest_value(self, root):
        return root.left.data if root.left is not None else root.data

    # Podar árbol (eliminar todos los nodos hoja)
    def prune(self):
        self.root = self.prune_recursive(self.root)

    def prune_recursive(self, node):
        if node is None:
            return None
        if node.left is None and node.right is None:
            return None
        node.left = self.prune_recursive(node.left)
        node.right = self.prune_recursive(node.right)
        return node

    # Obtener nodo por nivel
    def get_node_by_level(self, level):
        self.print_level(self.root, level, 1)

    def print_level(self, node, target_level, current_level):
        if node is None:
            return
        if current_level == target_level:
            print(node.data, end=" ")
        self.print_level(node.left, target_level, current_level + 1)
        self.print_level(node.right, target_level, current_level + 1)

    # Recorrido en preorden
    def preorder(self):
        self.preorder_traversal(self.root)
        print()

    def preorder_traversal(self, node):
        if node is not None:
            print(node.data, end=" ")
            self.preorder_traversal(node.left)
            self.preorder_traversal(node.right)

    # Recorrido en postorden
    def postorder(self):
        self.postorder_traversal(self.root)
        print()

    def postorder_traversal(self, node):
        if node is not None:
            self.postorder_traversal(node.left)
            self.postorder_traversal(node.right)
            print(node.data, end=" ")

    # Verificar si el árbol es completo
    def is_complete(self):
        return self.is_complete_tree(self.root, 0, self.count_nodes(self.root))

    def is_complete_tree(self, node, index, number_of_nodes):
        if node is None:
            return True
        if index >= number_of_nodes:
            return False
        return (self.is_complete_tree(node.left, 2 * index + 1, number_of_nodes) and
                self.is_complete_tree(node.right, 2 * index + 2, number_of_nodes))

    def count_nodes(self, node):
        if node is None:
            return 0
        return 1 + self.count_nodes(node.left) + self.count_nodes(node.right)

    # Verificar si un nodo tiene hijos
    def has_children(self, data):
        node = self.find_node(self.root, data)
        return node is not None and (node.left is not None or node.right is not None)

    def find_node(self, current, data):
        if current is None:
            return None
        if data == current.data:
            return current
        return self.find_node(current.left, data) if data < current.data else self.find_node(current.right, data)

# Ejemplo de uso
if __name__ == "__main__":
    tree = BinaryTree()
    tree.add(5)
    tree.add(3)
    tree.add(7)
    tree.add(2)
    tree.add(4)
    tree.add(6)
    tree.add(8)

    print("Recorrido en orden:")
    tree.inorder_traversal(tree.root)

    print("\nRecorrido en preorden:")
    tree.preorder()

    print("Recorrido en postorden:")
    tree.postorder()

    print("¿Es el árbol completo?", tree.is_complete())

    print("¿El nodo 7 tiene hijos?", tree.has_children(7))

    print("Nodos en el nivel 2:")
    tree.get_node_by_level(2)

    tree.remove(7)
    print("\nÁrbol después de eliminar el nodo 7:")
    tree.inorder_traversal(tree.root)

    tree.prune()
    print("\nÁrbol después de podar:")
    tree.inorder_traversal(tree.root)
