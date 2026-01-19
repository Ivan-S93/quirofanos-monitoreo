package py.gov.hospital.quirofanos_monitor.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import py.gov.hospital.quirofanos_monitor.dto.CirugiaActivaDTO;
import py.gov.hospital.quirofanos_monitor.dto.QuirofanoEstadoDTO;
import py.gov.hospital.quirofanos_monitor.model.enums.EstadoCirugia;
import py.gov.hospital.quirofanos_monitor.repository.CirugiaRepository;
import py.gov.hospital.quirofanos_monitor.repository.QuirofanoRepository;

@Service
public class QuirofanoService {

    private final QuirofanoRepository quirofanoRepository;
    private final CirugiaRepository cirugiaRepository;

    public QuirofanoService(QuirofanoRepository quirofanoRepository,
                             CirugiaRepository cirugiaRepository) {
        this.quirofanoRepository = quirofanoRepository;
        this.cirugiaRepository = cirugiaRepository;
    }

    /**
     * Devuelve el estado actual de todos los quirófanos
     * listo para ser consumido por el frontend.
     */

    public List<QuirofanoEstadoDTO> obtenerEstadoActual() {
        return quirofanoRepository.findAll().stream().map(q -> {
            var cirugiaOpt = cirugiaRepository.findByQuirofanoIdAndEstado(q.getId(), EstadoCirugia.EN_CURSO);
            CirugiaActivaDTO cirugiaDTO  = null;

            if (cirugiaOpt.isPresent()) {
                var c = cirugiaOpt.get();
                long minutos = Duration.between(c.getHoraInicio(), LocalDateTime.now()).toMinutes();

            cirugiaDTO = CirugiaActivaDTO.builder()
                    .id(c.getId())
                    .descripcion(c.getDescripcion())
                    .horaInicio(c.getHoraInicio())
                    .duracionEstimada((int) minutos)
                    .build();
            }

            return QuirofanoEstadoDTO.builder()
                    .id(q.getId())
                    .nombre(q.getNombre())
                    .tipo(q.getTipo())
                    .estado(q.getEstado().name())
                    .cirugiaActiva(cirugiaDTO)
                    .build();
        }).collect(Collectors.toList());

    }
    
}
