package com.riwi._2_taller_springboot.services;

import com.riwi._2_taller_springboot.entities.Eventos;
import com.riwi._2_taller_springboot.repositories.EventosRepository;
import com.riwi._2_taller_springboot.services.abstract_service.IEventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventoService implements IEventoService{
    @Autowired
    private final EventosRepository objEventoRepository;
    @Override
    public Eventos save(Eventos objEvento) {
        return this.objEventoRepository.save(objEvento);
    }
    @Override
    public List<Eventos> getAll() {
        return this.objEventoRepository.findAll();
    }
    @Override
    public Eventos getById(String id) {
        return this.objEventoRepository.findById(id).orElseThrow()  ;
    }
    @Override
    public void delete(String id) {
        Eventos evento = this.objEventoRepository.findById(id).orElseThrow();
        this.objEventoRepository.delete(evento);
    }

    @Override
    public Eventos update(Eventos objEvento) {
        return this.objEventoRepository.save(objEvento);
    }

    @Override
    public Page<Eventos> getAllPaginate(int page, int size) {
        if (page < 0){
            page = 0;
        }

        Pageable objPage = PageRequest.of(page, size);

        return this.objEventoRepository.findAll(objPage);
    }
}
