package com.riwi._2_taller_springboot.repositories;

import com.riwi._2_taller_springboot.entities.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Eventos, String> {

}
