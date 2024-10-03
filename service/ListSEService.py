from model.ListSE import ListSE

class ListSEService:
    def __init__(self):
        self.list_se = ListSE()

    def show_kids(self):
        return self.list_se.show_kids()
