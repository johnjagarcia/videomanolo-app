package com.manolo.videomanoloapp.view;

import java.io.IOException;
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

		FacesContext context = FacesContext.getCurrentInstance();

		if (cliente.getRut() != null && cliente.getNombre() != null) {

			Cliente clienteRegistrado = clientesRepository.findByRut(cliente.getRut());

			if (clienteRegistrado != null) {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Info.", "El cliente ya existe!."));
			} else {
				Cliente clienteGuardado = clientesRepository.save(cliente);
				if (clienteGuardado != null) {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info.",
							"Cliente registrado correctamente!."));
					init();

					/*try {
						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("peliculas.xhtml?faces-redirect=true");
					} catch (IOException e) {
						e.printStackTrace();
					}*/
				}
			}
		} else {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Info.", "Debe rellenar los campos obligatorios!"));
		}

	}

	public void reset() {
		init();
	}

	public void home() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("peliculas.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
