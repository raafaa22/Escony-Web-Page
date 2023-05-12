document.addEventListener('DOMContentLoaded',function () {
    let el = selector => document.querySelector(selector);

    class ctrlRegistro {
        constructor() {
            this.srvUrl = "/api/Cliente";
            //view-model
            this.usuarios = [];
        }

        init() {
            this.cargaUsuarios();
        }

        cargaUsuarios() {
            return fetch( this.srvUrl )
                .then(response => response.json()) //Promise resolve handler
                .then( usuarios => { //Promise resolve handler
                    this.usuarios=usuarios;
                    this.visualizaUsuarios();
                    return true;
                })
                .catch(() => { //Network error
                    el('#errores').innerHTML="Error en conexión";
                    console.error("Error en conexión");
                    return false;
                });
        }
        visualizaUsuarios() {
            let ul = el('#lista');
            ul.innerHTML = '';
            this.usuarios.forEach(usuarios => {
                let li = document.createElement('li');
                li.innerHTML = `<b>${usuarios.email}</b> ${usuarios.contrasena}`;
                ul.appendChild(li);
            });
        }


        validarDatos(event) {
            event.preventDefault(); //stop form submit
            var nombre = el('#fCliente\\:idNombre').value;
            var email = el('#fCliente\\:idEmail').value;
            var contrasena = el('#fCliente\\:idContrasena').value;
            var valido = true;

            if (nombre.length < 3 || nombre.length > 15) {
                el('#errNombre')
                    .textContent = "La longitud del nombre no es válida";
                valido = false;
            }

            if (email.search(/^\d{7,8}(-?[a-z])?$/i) === -1) {
                el('#errEmail')
                    .textContent = "email no válido ";
                valido = false;
            }

            const passwordRegex = /^(?=.[a-z])(?=.[A-Z])(?=.*\d).{6,}$/;
            if (!passwordRegex.test(contrasena)) {
                el('#errcontrasena')
                    .textContent = "La contraseña no cumple los requisitos: MAYUSCULA, MINUSCULA Y UN NUMERO ";
                valido = false;
            }
            if (contrasena.length < 8) {
                el('#errcontrasena')
                    .textContent = "La contraseña no cumple los requisitos: 8 digitos minimo";
                valido = false;
            }

            if (valido) el('#fCliente').submit(); //continue form submission

        }

    }

    window.addEventListener('load', () => {
//Create and initialize controller
        window.ctrl = new ctrlRegistro();
        console.log('Inicializando Inicio Sesion');
        ctrl.init();
    });
    })

