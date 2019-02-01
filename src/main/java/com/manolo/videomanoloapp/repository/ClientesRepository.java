package com.manolo.videomanoloapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manolo.videomanoloapp.model.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long> {
}
