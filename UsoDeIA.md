
## Uso de Inteligencia Artificial (IA) en el Proyecto

En base a lo estipulado en el README.md del proyecto, se ha decidido documentar el uso de la Inteligencia Artificial (IA) en el desarrollo del mismo. Esta decisión fue tomada para no llenar el codigo de diversas anotaciones, casi sin valor. Y el hecho de que, tener todos los cambios hechos con IA en un solo lugar, permite ver de una manera más clara y sencilla, el uso que se le ha dado a la IA en el proyecto.

### Configuración Inicial y Pruebas Básicas
- Autocompletado: Se utilizó la IA para autocompletar codigo para funciones sencillas (getters y setters) y strings repetitivos o similares (como el mensaje de error de las Exceptions). En los test, tambies se usó para crear facilmente los libros.
- ISBN: Como desconocia el término, use la IA para que me explique que es, su uitlidad y su formato. En la respuesta me paso funciones para validarlo (cosa que pensaba hacer). Asi que, use dicho codigo como base, cambiando algunas cosas para adaptarlo a mi proyecto.

### Implementación del Catálogo
- Autocompletado: Se utilizó la IA para autocompletar codigo para funciones sencillas (getters y setters) y strings repetitivos o similares (como el mensaje de error de las Exceptions). 
- Error en la instanciacion de Libros: Se utilizo IA para instancia diversos libros, pero puso los parametros mal (ISBN, Titulo y Autor). En el momento no me di cuenta, cree el resto de test siguiendo ese formato /orden. Cuando corro los tets y me dy cuenta que esta mal. Le pido devuelta a la IA que ponga el formato correcto en todos los test y en todas las instanciaciones de libros.

### Sistema de Préstamos
- Autocompletado: Se utilizó la IA para autocompletar codigo para funciones sencillas (getters y setters) y strings repetitivos o similares (como el mensaje de error de las Exceptions). 
- Creacion de Usuario: Al crear la clase Prestamo, cambie bastante la implementacion que tenia pensada. Dentro de esos cambios, la clase Prestamo esta mucho mas conectada a las clases Libro y Usuario. Pero, la clase Usuario no estaba creada todavia (es, por asi decirlo, responsabildiad de la proxima etapa.) Asi que, se utilizo IA para generar la "base" de la clase Usuario, y que esta tenga los atributos y metodos necesarios para que la clase Prestamo funcione. Todo de forma bastante temporal, ya que en la proxima etapa se revisara y corregira.

### Sistema de Usuarios
- Autocompletado: Se utilizó la IA para autocompletar codigo para funciones sencillas (getters y setters) y strings repetitivos o similares (como el mensaje de error de las Exceptions).
- Correcto uso de mocks: Revisando como hice los otros test para llevar cierta coherencia, me di cuenta que no estaba usando correctamente los mocks. Por lo que, se utilizo IA para corregir el uso de los mocks en los test, tanto de Usuarios, como de Libros y Prestamos (y sus correspondientes servicios)
- ContainerTest: El punto anterior me llevo a crear una clase Container que se encargue de crear los mocks de los test. En este caso, la IA me ayudo a crear la clase ContainerTest. La idea es que, al ser una clase generica, se pueda usar en todos los test y no tener que repetir el mismo codigo en cada uno de ellos. Tuve muchos problemas y correciones, pero llegue a un punto en el que estoy conforme.

### Conclusion
