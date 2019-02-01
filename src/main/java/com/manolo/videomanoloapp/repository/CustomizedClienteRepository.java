package com.manolo.videomanoloapp.repository;

import com.manolo.videomanoloapp.model.Cliente;

public interface CustomizedClienteRepository {

	Cliente findByRut(String rut);
}
