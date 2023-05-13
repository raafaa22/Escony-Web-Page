let el = selector => document.querySelector(selector);
class ctrlUsuarios {
    constructor() {
        this.srvUrl = "api/Cliente"; //REST service url
        this.usuarios = [];
        this.usuarioEditado = null; // Agregamos la propiedad usuarioEditado
        this.modoEdicion = false;
    }

    init() {
        el('#fAlta').onsubmit = (event) => {
            this.alta(event);
        };
        this.cargaUsuarios();
    }


    validateForm(event) {

        let nombre = document.getElementById('idNombre');
        let direccion = document.getElementById('idDireccion');
        let email = document.getElementById('idEmail');
        let contrasena = document.getElementById('idContrasena');

        let errors = [];

        // Comprobar que los campos requeridos no estén vacíos

        if (nombre.value.trim() === '') {
            errors.push('Por favor, proporcione un nombre válido.');
        }

        if (direccion.value.trim() === '') {
            errors.push('Por favor, proporcione una direccion válida.');
        }

        if (email.value.trim() === '') {
            errors.push('Por favor, proporcione un email válido.');
        }

        if (contrasena.value.trim() === '') {
            errors.push('Por favor, proporcione una contraseña válida.');
        }


        // Si hay errores, mostrarlos y evitar que se envíe el formulario
        if (errors.length > 0) {
            alert(errors.join('\n'));
            return false;
        }

        return true;
    }

    cargaUsuarios() {
        return fetch( this.srvUrl )
            .then(response => response.json()) //Promise resolve handler
            .then( usuarios => { //Promise resolve handler
                this.usuarios=usuarios;
                console.log(usuarios);
                this.visualizaUsuarios();
                return true;
            })
            .catch(() => { //Network error
                el('#errores').innerHTML="Error en conexión";
                console.error("Error en conexión");
                return false;
            });
    }
    guardarEdicion() {

        let nombre = document.getElementById('idNombre');
        let direccion = document.getElementById('idDireccion');
        let email = document.getElementById('idEmail');
        let contrasena = document.getElementById('idContrasena');

        if (this.usuarioEditado) {
            // Modificar el usuario existente
            this.usuarioEditado.nombre = nombre.value;
            this.usuarioEditado.direccion = direccion.value;
            this.usuarioEditado.email = email.value;
            this.usuarioEditado.contrasena = contrasena.value;

            this.editarUsuario(this.usuarioEditado.id)
                .then(() => {
                    // Actualizar formulario
                    document.getElementById('fAlta').reset();
                    // Actualizar lista de usuarios
                    this.cargaUsuarios();
                    // Reiniciar la variable usuarioEditado
                    this.usuarioEditado = null;
                })
                .catch((error) => {
                    console.error(error);
                });
        } else {
            // Crear un nuevo usuario
            let usuario = {
                nombre: nombre.value,
                direccion: direccion.value,
                email: email.value,
                contrasena: contrasena.value
            };

            this.alta(usuario)
                .then(() => {
                    // Actualizar formulario
                    document.getElementById('fAlta').reset();
                    // Actualizar lista de usuarios
                    this.cargaUsuarios();
                })
                .catch((error) => {
                    console.error(error);
                });
        }
    }

    getUsuarioById(id) {
        return this.usuarios.find(usuario => usuario.id === parseInt(id));
    }

    editarUsuario(id) {
        let usuario = this.getUsuarioById(id);
        if (usuario) {
            document.getElementById('idNombre').value = usuario.nombre;
            document.getElementById('idDireccion').value = usuario.direccion;
            document.getElementById('idEmail').value = usuario.email;
            document.getElementById('idContrasena').value = usuario.contrasena;

            this.usuarioEditado = usuario;
            this.modoEdicion = true;
            //this.guardarEdicion();
        }
    }

    visualizaUsuarios() {
        let ul = el('#usuarios');
        ul.innerHTML = '';

        this.usuarios.forEach(usuario => {
            console.log(usuario);
            let li = document.createElement('li');
            li.innerHTML = `
            <b>Nombre: </b>${usuario.nombre} <b>Direccion: </b>${usuario.direccion} 
            <b>Email: </b>${usuario.email} <b>Contraseña : </b>${usuario.contrasena}
            <button onclick="ctrl.editarUsuario('${usuario.id}')">Editar</button>
            <button onclick="ctrl.borrarUsuario('${usuario.id}')">Borrar</button>
    
                                </br></br>`;
            ul.appendChild(li);
        });
    }
    async alta(event) {
        event.preventDefault();
        if(this.validateForm(event)) {

            let nombre = document.getElementById('idNombre');
            let direccion = document.getElementById('idDireccion');
            let email = document.getElementById('idEmail');
            let contrasena = document.getElementById('idContrasena');


            console.log('Alta de usuario %s, %s, %s: %s, %s, %s', id, nombre, direccion, email, contrasena1, contrasena2);

            let usuario = {

                nombre: nombre,
                direccion: direccion,
                email: email,
                contrasena: contrasena
            };

            if (await this.enviaUsuario(usuario)) {
                el('#fAlta').reset();
                await this.cargaUsuarios();
            }
        }
        else{
            alert('Ha ocurrido un error al enviar el usuario: ' + error.message);
        }
    }

    async enviaUsuario(usuario) {
        let enviado = false;
        let errores = "";

        try {
            let url = this.srvUrl;
            let method = 'POST';

            if (this.modoEdicion) {
                // Si está en modo de edición, realizar una solicitud de actualización
                url += `/${usuario.id}`;
                method = 'PUT';
            }

            const response = await fetch(url, {
                method: method,
                body: JSON.stringify(usuario),
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (response.ok) {
                const data = await response.json();
                enviado = true;
                console.log(`Confirmada alta/edición de usuario: ${data.id}`);
            } else {
                const errorData = await response.json();
                errores = errorData[0].message;
                console.warn(errorData);
            }
        } catch (ex) {
            errores = "Error en la conexión";
            console.error(errores);
        }

        el('#errores').innerHTML = errores;
        return enviado;
    }


    async borrarUsuario(id) {
        try {
            let response = await fetch(`${this.srvUrl}/${id}`, {
                method: 'DELETE',
                mode: 'cors',
                headers: {
                    'Content-type': 'application/json'
                }
            });
            let data = await response.json();
            if (response.ok) {
                console.log(`Usuario con email ${email} borrado correctamente.`);
                this.cargaUsuarios(); // Actualiza la lista de usuarios
                return true;
            } else {
                console.warn(`Error al borrar usuario con email ${email}: ${data.message}`);
                return false;
            }
        } catch (ex) {
            console.error(`Error de conexión al borrar usuario con email ${email}: ${ex}`);
            return false;
        }
    }


}
window.addEventListener('load', () => {
    // Create and initialize controller
    window.ctrl = new ctrlUsuarios();
    console.log('Inicializando controlador usuario');
    ctrl.init();
});
