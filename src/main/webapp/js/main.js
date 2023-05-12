import "bootstrap/dist/css/bootstrap.min.css"


import { createApp } from 'vue/dist/vue.esm-bundler'

const AppRopa = {
    data() {
        return{
            clientes: [
                {nombre: "Rafa", email: "rafa@email"}
            ],
            cliente: {},
            errores: {}
        }
    },
    template: '<h2>Ropa</h2>'
}

createApp(AppRopa).mount("#app")
