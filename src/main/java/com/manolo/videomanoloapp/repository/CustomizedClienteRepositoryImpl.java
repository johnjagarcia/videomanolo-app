package com.manolo.videomanoloapp.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.manolo.videomanoloapp.model.Cliente;

@Repository
public class CustomizedClienteRepositoryImpl implements CustomizedClienteRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Cliente findByRut(String rut) {
		Query query = entityManager.createQuery("FROM cliente where rut = :rut", Cliente.class);
		query.setParameter("rut", rut);

		System.out.println(query.getSingleResult());

		return (Cliente) query.getSingleResult();
	}

}
