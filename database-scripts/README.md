1. CoffeeLink

-El api-core:

Es servicio de Java/Spring (en el puerto 8080).

Es el único que habla con la base de datos (PostgreSQL).

-El Perro guau guau:

Maneja el POST /api/login y "fabrica" los pases de acceso (JWTs).

Un filtro que intercepta todas las peticiones para revisar el token (el JWT).

Si la petición pasa, el BFF (Guardia) llama al api-core (Cerebro) para pedirle los datos.

2.  La Arquitectura

El API-Core (Puerto 8080)

El BFF (Puerto 8081)

El Frontend (React, Puerto 3000)."

3.  El Modelo de Negocio

E-commerce CoffeeLink.

Es una tienda digital especializada. No es un marketplace (como Amazon) donde cualquiera puede vender.

El rol admin es el dueño de la tienda. Él es quien prueba cafés, compra los mejores al por mayor, y usa el panel de /admin para subir ese inventario (stock) a la tienda.

El rol cliente entra a la tienda, ve un catálogo seleccionado (solo los mejores cafés) y los compra.
