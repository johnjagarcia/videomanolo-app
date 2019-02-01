package com.manolo.videomanoloapp.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.manolo.videomanoloapp.model.Cliente;
import com.manolo.videomanoloapp.repository.ClientesRepository;

@Named
@ViewScoped
public class ClientesView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClientesRepository clientesRepository;

	private List<Cliente> clientes;

	private Cliente cliente;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		clientes = clientesRepository.findAll();
	}

	public void submit() {
		if (cliente.getRut() != null && cliente.getNombre() != null) {

			Cliente clienteGuardado = clientesRepository.save(cliente);
			if (clienteGuardado != null) {
				System.out.println("Usuario guardado correctamente");
				init();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Hay campos que obligatorios."));
		}

	}

	public void reset() {
		cliente = new Cliente();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
