package com.example.escony;

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
    private Map<String,String> credenciales; //ejemplo de almacén de credenciales
    public RopaIdentityStore() {
        credenciales = new HashMap<>();
        credenciales.put("usuario1", "clave1");
        credenciales.put("usuario2", "clave2");
    }

    public CredentialValidationResult validate (
            UsernamePasswordCredential usernamePasswordCredential ) {
//Recuperar credenciales proporcionadas por el servidor
        String username = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();
//Ejemplo simple de verificación de credenciales
        String validPassword = credenciales.get(username);
        if (validPassword != null && validPassword.equals(password)) {
//Autenticación completada, obtener los roles del usuario...
            Set<String> roles = new HashSet<>(Arrays.asList("USUARIOS"));
//Pasar datos del usuario al servidor
            return new CredentialValidationResult(username, roles);
        }
        return INVALID_RESULT; //Autenticación inválida
    }


}
