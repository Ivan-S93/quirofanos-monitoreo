package py.gov.hospital.quirofanos_monitor.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuirofanoEstadoDTO {
    private Long id;
    private String nombre;
    private String tipo;
    private String estado;
    private CirugiaActivaDTO cirugiaActiva;

    // Datos de la cirugia activa
    private String descripcion;
    private LocalDateTime horaInicio;
    private Integer duracionEstimada;

    // Ultima cirugia finalizada
    private String ultimaDescripcion;
    private LocalDateTime ultimaHoraFin;
}
