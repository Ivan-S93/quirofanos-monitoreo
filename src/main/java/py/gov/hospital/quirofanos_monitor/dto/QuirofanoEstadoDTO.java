package py.gov.hospital.quirofanos_monitor.dto;

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
}
