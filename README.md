# ESCONY
**Se trata de una página web de ropa donde los usuarios podrán encontrar las prendas de ropa actuales a la venta. Algunas estarán disponibles únicamente online y otras sí que estarán también en tienda. El usuario si lo desea también podrá comprarlas desde esa misma página**
**![](https://lh6.googleusercontent.com/CGjBBYXqccyk6V-vKhTy4EeOIZF6L9e9gd3DuV_aHF1t0GWUy7pigRDEkUX_M3B_tDRJ_EjNsVs6uRDIoHL-kf6B5zE2hVGlf8tZEZ-d0qa02yUAziUIDQTjD7caumcyklH4gcJSU5RAsQxqdy-VszU)**
-   Miembros del equipo de desarrollo definitivo:
    

Rafael Serrano Algar

Lydia Muñoz Gallardo

## Diagrama entidad/relación
Atributos marcados con * son la clave.
**R1: tiene**
**R2: tiene**
```mermaid
graph 
Tienda --- C{R2}
C-->Usuarios
Tienda---A{R1}
A-->Ropa
Dirección_ti---Tienda
*Id_ti---Tienda
Nombre_ti---Tienda
*Id_ropa---Ropa
Sexo---Ropa
Tipo---Ropa
Ropa---Precio
Ropa---Talla
*Email---Usuarios
Nombre_usu---Usuarios
Usuarios---Apellidos
Usuarios---Contraseña
Usuarios---Direccion_usu
```

