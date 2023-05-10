package com.example.escony;

import com.example.escony.model.Cliente;
import com.example.escony.model.dao.ClienteDAO;
import com.example.escony.qualifiers.DAOMap;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/Cliente") //Acceso /api/libros
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped //Ojo, jakarta.enterprise.context.RequestScoped

public class UsuariosResource {
    @Inject
    @DAOMap
    private ClienteDAO clienteDAO;

    @GET
    public List<Cliente> listado() {
        return clienteDAO.buscaTodos();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevocliente( @Valid Cliente l) {
        clienteDAO.creaCliente(l);
        return Response.ok(l).build();
    }
}
