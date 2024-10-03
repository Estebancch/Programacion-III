from fastapi import APIRouter
from service.ListSEService import ListSEService
from model.Kid import Kid

router = APIRouter()
list_se_service = ListSEService()

# Añadir un niño a la lista SE
@router.post("/listse/add")
def add_kid(kid: Kid):
    list_se_service.add(kid)
    return {"message": "Niño añadido a la lista SE"}

# Mostrar todos los niños en la lista SE
@router.get("/listse/show")
def show_kids():
    kids = list_se_service.show_kids()
    return {"kids": kids}

# Añadir niño en una posición específica en la lista SE
@router.post("/listse/addinposition")
def add_kid_in_position(kid: Kid, position: int):
    list_se_service.add_in_position(kid, position)
    return {"message": f"Niño añadido en la posición {position}"}

# Eliminar un niño por ID en la lista SE
@router.delete("/listse/deletebyid/{kid_id}")
def delete_kid_by_id(kid_id: int):
    list_se_service.delete_by_id(kid_id)
    return {"message": f"Niño con ID {kid_id} eliminado"}

# Eliminar niño por posición en la lista SE
@router.delete("/listse/deletebyposition/{position}")
def delete_kid_by_position(position: int):
    list_se_service.delete_by_position(position)
    return {"message": f"Niño en la posición {position} eliminado"}

# Invertir la lista SE
@router.put("/listse/invert")
def invert_list():
    list_se_service.invert()
    return {"message": "Lista SE invertida"}

# Intercambiar extremos en la lista SE
@router.put("/listse/swap-extremes")
def swap_extremes():
    list_se_service.swap_extremes()
    return {"message": "Extremos de la lista SE intercambiados"}
