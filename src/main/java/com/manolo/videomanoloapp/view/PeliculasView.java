package com.manolo.videomanoloapp.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
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

	private Long selectedId;

	@PostConstruct
	public void init() {
		peliculas = peliculaRepository.findAll();
	}

	public void successs() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("reserva.xhtml?id_pelicula=" + selectedPelicula.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public Long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}

}
