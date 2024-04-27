package com.riwi._2_taller_springboot.controllers;

import com.riwi._2_taller_springboot.entities.Eventos;
import com.riwi._2_taller_springboot.services.abstract_service.IEventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos")
@AllArgsConstructor
public class EventoController {
    @Autowired
    private final IEventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Eventos>> getAll(){
        return ResponseEntity.ok(this.eventoService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Eventos> get(@PathVariable String id){
        return ResponseEntity.ok(this.eventoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Eventos> insert(@RequestBody Eventos objEvento){
        ResponseEntity<Eventos>valid=null;
        if (!LocalDate.now().isAfter(objEvento.getFecha())){
            if (objEvento.getCapacidad()<0){
                objEvento.setCapacidad(0);
                valid= ResponseEntity.ok(this.eventoService.update(objEvento));
            }else {
                valid = ResponseEntity.ok(this.eventoService.update(objEvento));
            }
        }else {
            System.out.println("NO PASA");
        }
        return valid;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Eventos> update(
            @PathVariable String id,
            @RequestBody Eventos objEvento
    ){
        return ResponseEntity.ok(this.eventoService.update(objEvento));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/get_all")
    public ResponseEntity<Page<Eventos>>  getAllPaginate(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size
    ){
        /*Page<Eventos> listEventos = this.eventoService.getAllPaginate(page -1,size);
        return listEventos.getContent();*/
        return ResponseEntity.ok(this.eventoService.getAllPaginate(page - 1, size));
    }

}
























