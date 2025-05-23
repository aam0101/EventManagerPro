Gestión de Eventos y Venta de Entradas
Descripción
Este proyecto en Java implementa una aplicación para la gestión de eventos y la venta de entradas. Utiliza una base de datos MySQL para almacenar información sobre eventos y entradas, y una interfaz gráfica desarrollada con Swing para facilitar la interacción con el usuario.

Características principales
Crear, modificar y eliminar eventos.

Visualizar la lista de eventos disponibles.

Registrar la venta de entradas para eventos específicos.

Generar informes sobre las entradas vendidas.

Interfaz gráfica sencilla e intuitiva.

Tecnologías usadas
Java SE

Swing (para interfaz gráfica)

MySQL (base de datos)

JDBC (para conexión a la base de datos)

Instalación y ejecución
Asegúrate de tener instalado Java JDK y MySQL.

Configura la base de datos MySQL y crea la base con las tablas necesarias (evento, entrada, etc.).

Modifica la clase Conexion.java para que contenga los datos correctos de conexión (URL, usuario, contraseña).

Compila el proyecto con tu IDE favorito (Eclipse, IntelliJ, NetBeans) o desde consola.

Ejecuta la clase principal Main.java para iniciar la aplicación.

Navega por la interfaz para gestionar eventos y entradas.

Estructura del proyecto
Modelo: Clases Evento, Entrada, etc.

DAO: Clases que manejan acceso y manipulación de datos (EventoDAO, EntradaDAO).

Vistas: Clases con ventanas Swing (VentanaInicio, VentanaAgregarEvento, VentanaEventos, VentanaEntradas, VentanaInformeEntradas).

Utilidad: Clase para conexión a base de datos (Conexion).

Uso básico
Desde la ventana inicial se puede acceder a la gestión de eventos, agregar nuevos eventos, vender entradas o consultar informes.

En la gestión de eventos se pueden modificar o eliminar eventos existentes.

En la venta de entradas se selecciona un evento y se registra la venta.

Los informes permiten visualizar datos resumidos de las ventas.

Contribuciones
Las contribuciones son bienvenidas. Puedes crear un fork y enviar pull requests para mejorar funcionalidades o corregir errores.

Licencia
Este proyecto es para uso educativo y de práctica. No se incluye licencia explícita.
