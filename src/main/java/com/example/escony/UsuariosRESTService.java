package com.example.escony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import com.example.escony.model.Cliente;
import com.example.escony.model.dao.ClienteDAO;
import com.example.escony.qualifiers.DAOJPA;
import com.example.escony.qualifiers.DAOMap;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/Cliente")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UsuariosRESTService
{
    private final Logger log = Logger.getLogger( UsuariosRESTService.class.getName() );
    @Context
    private UriInfo context;

    @Inject     @DAOJPA
    ClienteDAO usuarioDAO;

    public UsuariosRESTService() {
    }

    @GET
    public List<Cliente> getUsuarios() {
        return usuarioDAO.buscaTodos();
    }

    @GET
    @Path("/{id}")
    public Response getUsuario(@PathParam("id") String email) {
        Response response;
        Cliente c=usuarioDAO.buscaEmail(email);
        if( c!=null) {
            response= Response.ok(c).build();
        } else {
            //Error messages (using Map for create generic json objects)
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El cliente no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @DELETE
    @Path("/{email}")
    public Response borraUsuario(@PathParam("email") String email)
    {
        Response response;

        if (usuarioDAO.borraCliente(email) )
        {
            response= Response.ok(email).build();
        } else {
            //Error messages (using Map for create generic json objects)
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "El cliente no existe");
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response creaUsuario(@Valid Cliente c) {
        log.info("hola");
        Response response;
        if (usuarioDAO.creaCliente(c))
        {
            response= Response.ok(c).build();
        } else {
            //Error messages (using Map for create generic json objects)
            List< Map<String,Object> > errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>();
            err.put("message", "No se ha podido crear el cliente");
            err.put("cliente", c);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificaUsuario(@Valid Cliente c, @PathParam("id") String email) {
        Response response;
        c.setEmail(email);
        if (usuarioDAO.guardaCliente(c)) {
            response= Response.ok(c).build();
        } else {
            //Error messages (using Map for create generic json objects)
            List<Map<String,Object>> errores=new ArrayList<>();
            Map<String,Object> err=new HashMap<>(); //Error messages
            err.put("message", "No se ha podido modificar el cliente");
            err.put("cliente", c);
            errores.add(err);
            response=Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores).build();
        }
        return response;
    }
}
