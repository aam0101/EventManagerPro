# Proyecto de Gestion de Eventos

## Descripción del Proyecto

Este proyecto Java implementa la conexión a una base de datos SQL utilizando JDBC y el uso del patrón DAO para realizar operaciones sobre la tabla `Entrada`. El objetivo principal es establecer una conexión segura mediante el uso de un Oracle Wallet, facilitando así el acceso a una base de datos remota sin necesidad de exponer credenciales sensibles.

## Sistema Gestor de Base de Datos (SGBD) Utilizado

- **Oracle Database** (ej. Oracle Autonomous Database en Oracle Cloud Infrastructure)
- Conexión mediante **JDBC** usando **Oracle Wallet** para mayor seguridad.

## Estructura del Proyecto

- `Conexion.java`: Clase responsable de establecer la conexión con la base de datos Oracle.
- `EntradaDAO.java`: Clase DAO que realiza operaciones sobre los registros de la tabla `Entrada`.

## Requisitos Previos

1. **JDK 8 o superior**
2. **Oracle JDBC Driver** (ojdbc8.jar u ojdbc11.jar)
3. **Wallet de Oracle** descargado desde Oracle Cloud (si se usa un Autonomous DB)
4. **IDE de desarrollo Java** como IntelliJ IDEA, Eclipse o NetBeans.

---

## Configuración del Entorno

### 1. Descargar el Driver JDBC

- Desde el sitio oficial de Oracle:  
  [https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html](https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html)
- Agrega el archivo `ojdbc8.jar` al classpath del proyecto.

### 2. Descargar y Configurar el Oracle Wallet (si aplica)

Si estás usando una base de datos autónoma en Oracle Cloud:

1. Inicia sesión en [https://cloud.oracle.com](https://cloud.oracle.com).
2. Ve al panel de tu base de datos autónoma.
3. Haz clic en **DB Connection**.
4. Descarga el **Wallet** (selecciona "Instance Wallet").
5. Extrae el contenido en una carpeta, por ejemplo: `./wallet`.

### 3. Configurar el archivo `sqlnet.ora`, `tnsnames.ora`, y `cwallet.sso`

Asegúrate de que el archivo `Conexion.java` apunte correctamente al directorio del Wallet y que el `tnsnames.ora` incluya la configuración de tu base de datos. Ejemplo de URL de conexión:

```java
String url = "jdbc:oracle:thin:@tu_alias_db?TNS_ADMIN=/ruta/a/tu/wallet";
