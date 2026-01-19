package py.gov.hospital.quirofanos_monitor.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CirugiaActivaDTO {
    private Long id;
    private String descripcion;
    private LocalDateTime horaInicio;
    private Integer duracionEstimada;
}
