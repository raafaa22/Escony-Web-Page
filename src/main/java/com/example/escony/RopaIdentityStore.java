package com.example.escony;

import com.example.escony.qualifiers.DAOJPA;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.security.enterprise.identitystore.IdentityStore;
import com.example.escony.model.Cliente;
import com.example.escony.model.Ropa;
import com.example.escony.model.dao.ClienteDAO;
import com.example.escony.qualifiers.DAOMap;
import java.util.Set;
import java.util.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Arrays;
@ApplicationScoped

public class RopaIdentityStore  implements IdentityStore {
    @Inject
    @DAOJPA
    ClienteDAO clientedao;
    public RopaIdentityStore() {

    }

    public CredentialValidationResult validate (
            UsernamePasswordCredential usernamePasswordCredential ) {
//Recuperar credenciales proporcionadas por el servidor
        String username = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();
        Cliente cliente=clientedao.buscaEmail(username);

//Ejemplo simple de verificación de credenciales
        if (cliente!=null && cliente.getPassword().equals(password)) {
//Autenticación completada, obtener los roles del usuario...
            Set<String> roles = new HashSet<>(Arrays.asList(cliente.getRol()));
//Pasar datos del usuario al servidor
            return new CredentialValidationResult(username, roles);
        }
        return INVALID_RESULT; //Autenticación inválida
    }


}
