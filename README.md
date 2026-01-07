# 🏥 Monitor de Gestión de Quirófanos en Tiempo Real

Este proyecto es un **Dashboard de Monitoreo** diseñado para optimizar la gestión y disponibilidad de quirófanos en entornos hospitalarios.
Permite visualizar en tiempo real el estado de cada sala, los tiempos de cirugía y las próximas programaciones, facilitando la toma de decisiones del personal médico.


## 🚀 Funcionalidades Clave

* **Visualización en Tiempo Real:** Actualización instantánea del estado de los quirófanos (Libre/Ocupado) mediante WebSockets.
* **Gestión de Estados:** Código de colores (Semáforo) para identificar rápidamente la disponibilidad.
* **Seguimiento de Cirugías:** Muestra el tipo de procedimiento, hora de inicio y tiempo estimado de finalización.
* **Contadores Dinámicos:** Cuenta regresiva y tiempo transcurrido para cada intervención activa.
* **Interfaz Adaptable:** Diseño responsivo para su visualización en estaciones de enfermería o monitores de pasillo.

## 🛠️ Stack Tecnológico

* **Frontend:** React.js con Tailwind CSS para una interfaz moderna y fluida.
* **Backend:** Spring Boot (Java) bajo una arquitectura limpia.
* **Base de Datos:** PostgreSQL para la persistencia de datos y gestión de historial.
* **Comunicación Live:** WebSockets (STOMP/SockJS) para el envío de eventos en tiempo real desde el servidor.

## 📂 Estructura del Proyecto (Backend)

```text
src/main/java/com/tu_paquete/
├── config/         # Configuración de WebSockets y Seguridad
├── controller/     # Endpoints de la API REST
├── model/          # Entidades de la Base de Datos
├── repository/     # Interfaces de acceso a datos (JPA)
├── service/        # Lógica de negocio y cálculo de tiempos
└── websocket/      # Manejo de mensajes en tiempo real
