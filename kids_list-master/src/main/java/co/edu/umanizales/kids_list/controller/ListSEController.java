package co.edu.umanizales.kids_list.controller;

import co.edu.umanizales.kids_list.model.Kid;
import co.edu.umanizales.kids_list.model.Node;
import co.edu.umanizales.kids_list.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listse")
public class ListSEController {

    @Autowired
    private ListSEService listSEService;

    // Mostrar la lista completa
    @GetMapping
    public Node getListChildren() {
        return listSEService.showKids();
    }

    // Añadir un niño al final de la lista
    @PostMapping
    public String addKidToFinal(@RequestBody Kid kid) {
        listSEService.getListSE().add(kid);
        return "Adicionado exitosamente";
    }

    // Añadir un niño al inicio de la lista
    @PostMapping("/tostart")
    public String addKidToStart(@RequestBody Kid kid) {
        listSEService.getListSE().addToStart(kid);
        return "Adicionado exitosamente";
    }

    // Añadir un niño en una posición específica
    @PostMapping("/addinposition{position}")
    public String addKidInPosition(@RequestBody Kid kid, @RequestParam int position) {
        try {
            listSEService.getListSE().addInPosition(kid, position);
            return "Adicionado exitosamente en la posición " + position;
        } catch (IndexOutOfBoundsException e) {
            return "Error: " + e.getMessage();
        }
    }

    // Invertir la lista
    @PostMapping("/invert")
    public String invertList() {
        listSEService.getListSE().invert();
        return "Lista invertida exitosamente";
    }

    // Borrar un niño por ID
    @DeleteMapping("/deletebyid/{id}")
    public String deleteKidById(@PathVariable String id) {
        listSEService.getListSE().borrarxID(id);
        return "Niño con ID " + id + " eliminado exitosamente";
    }

    // Borrar un niño por posición
    @DeleteMapping("/deletebyposition/{position}")
    public String deleteKidByPosition(@PathVariable int position) {
        try {
            listSEService.getListSE().borrarxPosicion(position);
            return "Niño en la posición " + position + " eliminado exitosamente";
        } catch (IndexOutOfBoundsException e) {
            return "Error: " + e.getMessage();
        }
    }

    // Intercalar niños por género
    @PostMapping("/intercalaresgenero")
    public String intercalarPorGenero() {
        listSEService.getListSE().intercalarxGenero();
        return "Niños intercalados por género exitosamente";
    }

    // Intercalar extremos
    @PostMapping("/intercalaextremos")
    public String intercalarExtremos() {
        listSEService.getListSE().intercalarExtremos();
        return "Niños intercalados por extremos exitosamente";
    }
}
