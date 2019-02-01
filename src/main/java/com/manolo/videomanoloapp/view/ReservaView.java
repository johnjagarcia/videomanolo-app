package com.manolo.videomanoloapp.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.manolo.videomanoloapp.model.Cliente;
import com.manolo.videomanoloapp.model.Pelicula;
import com.manolo.videomanoloapp.model.Reserva;
import com.manolo.videomanoloapp.repository.ClientesRepository;
import com.manolo.videomanoloapp.repository.PeliculaRepository;
import com.manolo.videomanoloapp.repository.ReservaRepository;

@Named
@ViewScoped
public class ReservaView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ReservaRepository reservaRepository;

	@Inject
	private PeliculaRepository peliculaRepository;

	@Inject
	private ClientesRepository clientesRepository;

	private Cliente cliente;

	private Pelicula pelicula;

	private List<Reserva> reservas;

	@PostConstruct
	public void init() {
		cliente = new Cliente();

		reservas = reservaRepository.findAll();

		String peliculaId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("id_pelicula");
		loadPelicula(peliculaId);
	}

	public void loadPelicula(String peliculaId) {
		if (peliculaId != null && !peliculaId.isEmpty()) {
			Optional<Pelicula> optional = peliculaRepository.findById(Long.parseLong(peliculaId));
			if (optional.isPresent()) {
				pelicula = optional.get();
			}
		} else {
			new FacesMessage(FacesMessage.SEVERITY_WARN, "Info.", "Debe seleccionar una pelicula!");
		}
	}

	public void reservar() {

		System.out.println("Cliente rut: " + cliente.getRut());

		Cliente clienteObtenido = clientesRepository.findByRut(cliente.getRut());

		if (clienteObtenido != null) {
			Reserva reserva = new Reserva(clienteObtenido, pelicula, new Date());
			if (reservaRepository.save(reserva) != null) {
				System.out.println("Reserva guardada correctamente!");
				int nuevaCantidad = pelicula.getCantidad() - 1;
				pelicula.setCantidad(nuevaCantidad);
				if (peliculaRepository.save(pelicula) != null) {
					System.out.println("Cantidad actualizada correctamente!");
					init();
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("Info.",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info.", "Cliente no registrado! Por favor registrese"));
		}
	}

	public String toClientes() {
		return "clientes.xhtml?faces-redirect=true";
	}
	
	public String home() {
		return "peliculas.xhtml?faces-redirect=true";
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}
}
