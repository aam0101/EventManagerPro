# 📅🎟️ Proyecto Gestión de Eventos y Venta de Entradas

## 📝 Descripción

Esta es una aplicación de escritorio en **Java Swing** para gestionar eventos y la venta de entradas. Utiliza una base de datos **MySQL** para almacenar la información y ofrece interfaces gráficas amigables para facilitar la administración.

---

## 🗂️ Estructura del Proyecto

### 📁 Archivos principales

- **Main.java**  
  ▶️ Punto de inicio de la aplicación.

- **Conexion.java**  
  🔌 Maneja la conexión a la base de datos MySQL.

- **Evento.java**  
  🎉 Representa un evento.

- **EventoDAO.java**  
  💾 Operaciones para gestionar eventos en la base de datos.

- **EntradaDAO.java**  
  🎫 Operaciones para gestionar entradas vendidas.

---

### 🖥️ Interfaces gráficas (Ventanas Swing)

- **VentanaInicio.java**  
  🏠 Ventana principal para navegar entre opciones.

- **VentanaEventos.java**  
  📋 Lista y gestión de eventos.

- **VentanaAgregarEvento.java**  
  ➕ Formulario para agregar nuevos eventos.

- **VentanaEntradas.java**  
  🎟️ Venta y gestión de entradas.

- **VentanaInformeEntradas.java**  
  📊 Informes y estadísticas de ventas.

---

## ⚙️ Requisitos

- ☕ Java JDK 8 o superior  
- 🐬 MySQL con las tablas configuradas  
- 🔗 Librería JDBC Connector para MySQL

---

## 🔧 Configuración

1. Crear la base de datos y tablas necesarias en MySQL.  
2. Configurar la conexión en `Conexion.java` con tus datos (host, usuario, contraseña).  
3. Compilar y ejecutar `Main.java` para iniciar la app.

---

## 🚀 Uso

- Desde la ventana principal 🏠, accede a la gestión de eventos para crear o modificar eventos.  
- En la ventana de entradas 🎟️, vende entradas para los eventos disponibles.  
- Consulta los informes 📊 para revisar estadísticas de ventas.

---

## 💡 Posibles mejoras

- ✅ Validaciones y manejo de errores más robustos.  
- 🎨 Mejoras en la interfaz gráfica.  
- 📄 Exportar informes a PDF o Excel.  
- 🔐 Gestión de usuarios y permisos.

---

Si quieres, puedo ayudarte a crear un script para la base de datos o ampliar la documentación. ¡Solo dime! 😊
