# RappiPeliculas
Prueba desarrollador rappi

Prueba API Peliculas themoviedb.org

Rappi Peliculas es una app Android, se usan las librerias Retrofit2, Okhttp, ButherKnife, rxjava, Picasso.

La app cuenta con un home principal con una toolbar que permite filtrar las 3 categorias de las peliculas (popular top upcoming) tanto offline como online.

En esta pantalla se listan la peliculas en dos columnas, al precionar un de las peliculas nos permite acceder a su detalla y si esta cuenta con video nos permite poder visualizarlo.

Se busca en el codigo tener una arquitectura limpia, por lo cual se trata de que sea lo menos acoplado posible y que las clases o metodos tengan responsabilidades unicas y evitar que estas afecten a otras en caso de cambios o actualizaciones.

Se usa el patron MVP.

El directorio esta dividido en:

vista: Interfaz de usuario, aca se encuentran las actividades y los adaptadores que interactuan directamente con la interfaz grafica 

Network: En esta parte se encuentra las clases para hacer las peticiones a la api. 

Contratos: En esta se encuentra toda la parte de negocio. 

Modelo: Aca encontramos los modelos o clases que representan los objetos que retorna la API.

La consulta de los servicios tienen una cache de 10 megas, para que en caso de que el usuario no tenga conexion pueda de igual forma acceder a la app

Responsabilidad unica:

Una clase o modulo debe tener una única responsabilidad, esto quiere decir que una vista no tiene porque preocuparse de conectarse a una base de datos, si no de la parte grafica, dejando la logica a otra capa, y asi para las demas, logrando conseguir varias capas, facilitando actualizaciones, mantenimiento y testeo de las aplicaciones.

Cual es su proposito:

Su propósito es para no crear código acoplado, y así los cambios que se hagan a través del tiempo no interfieran con el trabajo de otras clases o métodos.

Características de código limpio:

Facilitar el testeo Que la interfaz gráfica tenga independencia de la base de datos y la logica o de negocio, facilidad de mantenimiento.
