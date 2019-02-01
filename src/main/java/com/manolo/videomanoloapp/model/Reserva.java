package com.manolo.videomanoloapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Reserva")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	@Column(name = "fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;

	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Cliente cliente;

	@JoinColumn(name = "id_pelicula", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Pelicula pelicula;

	public Reserva() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Reserva(Cliente cliente, Pelicula pelicula, Date fechaRegistro) {
		super();
		this.cliente = cliente;
		this.pelicula = pelicula;
		this.fechaRegistro = fechaRegistro;
	}

	@PostConstruct
	public void init() {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id_pelicula");
		System.out.println("PELICULA: " + id);
	}

	public Long getId() {
		return id;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}
}
