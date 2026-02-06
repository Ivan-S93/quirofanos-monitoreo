package py.gov.hospital.quirofanos_monitor.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import py.gov.hospital.quirofanos_monitor.dto.CirugiaActivaDTO;
import py.gov.hospital.quirofanos_monitor.dto.QuirofanoEstadoDTO;
import py.gov.hospital.quirofanos_monitor.model.Cirugia;
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
     * con cirugía activa y última cirugía finalizada.
     */
    public List<QuirofanoEstadoDTO> obtenerEstadoActual() {

        return quirofanoRepository.findAll().stream().map(q -> {

            // -------------------------
            // Cirugía activa
            // -------------------------
            CirugiaActivaDTO cirugiaDTO = null;

            /*cirugiaRepository
                .findFirstByQuirofanoIdAndEstadoOrderByHoraInicioDesc(
                        q.getId(), EstadoCirugia.EN_CURSO)
                .ifPresent(c -> {

                    long minutosTranscurridos =
                            Duration.between(c.getHoraInicio(), LocalDateTime.now()).toMinutes();

                    // Usamos un array para poder modificar dentro del lambda
                });*/

            // Lo hacemos fuera para que compile bien
            Cirugia cirugiaActiva = cirugiaRepository
                .findFirstByQuirofanoIdAndEstadoOrderByHoraInicioDesc(
                        q.getId(), EstadoCirugia.EN_CURSO)
                .orElse(null);

            if (cirugiaActiva != null) {
                long minutosTranscurridos =
                        Duration.between(cirugiaActiva.getHoraInicio(), LocalDateTime.now()).toMinutes();

                cirugiaDTO = CirugiaActivaDTO.builder()
                        .id(cirugiaActiva.getId())
                        .descripcion(cirugiaActiva.getDescripcion())
                        .horaInicio(cirugiaActiva.getHoraInicio())
                        .duracionEstimada(cirugiaActiva.getDuracionEstimada())
                        .minutosTranscurridos((int) minutosTranscurridos)
                        .build();
            }

            // -------------------------
            // Última cirugía finalizada
            // -------------------------
            Cirugia ultima = cirugiaRepository
                    .findFirstByQuirofanoIdAndEstadoOrderByHoraFinRealDesc(
                            q.getId(), EstadoCirugia.FINALIZADA)
                    .orElse(null);

            String ultimaDescripcion = null;
            LocalDateTime ultimaHoraFin = null;

            if (ultima != null) {
                ultimaDescripcion = ultima.getDescripcion();
                ultimaHoraFin = ultima.getHoraFinReal();
            }

            // -------------------------
            // Armar DTO
            // -------------------------
            return QuirofanoEstadoDTO.builder()
                    .id(q.getId())
                    .nombre(q.getNombre())
                    .tipo(q.getTipo())
                    .estado(q.getEstado().name())
                    .cirugiaActiva(cirugiaDTO)
                    .ultimaDescripcion(ultimaDescripcion)
                    .ultimaHoraFin(ultimaHoraFin)
                    .build();

        }).collect(Collectors.toList());
    }
}

