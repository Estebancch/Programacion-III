package co.edu.umanizales.kids_list.controller;

import co.edu.umanizales.kids_list.model.Kid;
import co.edu.umanizales.kids_list.model.NodeDE;
import co.edu.umanizales.kids_list.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listde")
public class ListDEController {

    @Autowired
    private ListDEService listDEService;

    // Mostrar la lista completa
    @GetMapping
    public NodeDE getListChildren() {
        return listDEService.showKids();
    }

    // Añadir un niño al final de la lista
    @PostMapping
    public String addKidToFinal(@RequestBody Kid kid) {
        listDEService.getListDE().add(kid);
        return "Adicionado exitosamente";
    }

    // Añadir un niño al inicio de la lista
    @PostMapping("/tostart")
    public String addKidToStart(@RequestBody Kid kid) {
        listDEService.getListDE().addToStart(kid);
        return "Adicionado exitosamente";
    }

    // Borrar un niño por ID
    @DeleteMapping("/deletebyid/{id}")
    public String deleteKidById(@PathVariable String id) {
        listDEService.getListDE().deleteById(id);
        return "Niño con ID " + id + " eliminado exitosamente";
    }
}
