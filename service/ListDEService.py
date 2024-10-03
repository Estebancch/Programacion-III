from model.ListDE import ListDE

class ListDEService:
    def __init__(self):
        self.list_de = ListDE()

    def show_kids(self):
        return self.list_de.show_kids()
