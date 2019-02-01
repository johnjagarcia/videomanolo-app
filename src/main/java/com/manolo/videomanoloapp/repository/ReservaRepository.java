package com.manolo.videomanoloapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manolo.videomanoloapp.model.Pelicula;
import com.manolo.videomanoloapp.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
