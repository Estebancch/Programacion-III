from fastapi import APIRouter
from service.ListDEService import ListDEService
from model.Kid import Kid

router = APIRouter()
list_de_service = ListDEService()

# Añadir un niño a la lista DE
@router.post("/listde/add")
def add_kid(kid: Kid):
    list_de_service.add(kid)
    return {"message": "Niño añadido a la lista DE"}

# Mostrar todos los niños en la lista DE
@router.get("/listde/show")
def show_kids():
    kids = list_de_service.show_kids()
    return {"kids": kids}

# Añadir niño en una posición específica en la lista DE
@router.post("/listde/addinposition")
def add_kid_in_position(kid: Kid, position: int):
    list_de_service.add_in_position(kid, position)
    return {"message": f"Niño añadido en la posición {position}"}

# Eliminar un niño por ID en la lista DE
@router.delete("/listde/deletebyid/{kid_id}")
def delete_kid_by_id(kid_id: int):
    list_de_service.delete_by_id(kid_id)
    return {"message": f"Niño con ID {kid_id} eliminado"}

# Eliminar niño por posición en la lista DE
@router.delete("/listde/deletebyposition/{position}")
def delete_kid_by_position(position: int):
    list_de_service.delete_by_position(position)
    return {"message": f"Niño en la posición {position} eliminado"}

# Invertir la lista DE
@router.put("/listde/invert")
def invert_list():
    list_de_service.invert()
    return {"message": "Lista DE invertida"}

# Intercalar niños por género en la lista DE
@router.put("/listde/interleavebygender")
def interleave_by_gender():
    list_de_service.interleave_by_gender()
    return {"message": "Lista DE intercalada por género"}

# Intercambiar extremos en la lista DE
@router.put("/listde/swapextremes")
def swap_extremes():
    list_de_service.swap_extremes()
    return {"message": "Extremos de la lista DE intercambiados"}
