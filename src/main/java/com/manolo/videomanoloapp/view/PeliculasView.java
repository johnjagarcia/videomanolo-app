package com.manolo.videomanoloapp.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.manolo.videomanoloapp.model.Pelicula;
import com.manolo.videomanoloapp.repository.PeliculaRepository;

@Named
@ViewScoped
public class PeliculasView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PeliculaRepository peliculaRepository;

	private List<Pelicula> peliculas;
	
	private Pelicula selectedPelicula;

	@PostConstruct
	public void init() {
		peliculas = peliculaRepository.findAll();
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public Pelicula getSelectedPelicula() {
		return selectedPelicula;
	}

	public void setSelectedPelicula(Pelicula selectedPelicula) {
		this.selectedPelicula = selectedPelicula;
	}
}
