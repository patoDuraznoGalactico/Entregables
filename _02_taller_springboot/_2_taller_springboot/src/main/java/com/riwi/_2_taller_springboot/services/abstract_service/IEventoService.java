package com.riwi._2_taller_springboot.services.abstract_service;

import com.riwi._2_taller_springboot.entities.Eventos;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEventoService {

    public Eventos save(Eventos objEvento);
    public List<Eventos> getAll();
    public Eventos getById(String id);
    public void delete(String id);
    public Eventos update(Eventos objEvento);
    public Page<Eventos> getAllPaginate(int page, int size);

}
