package py.gov.hospital.quirofanos_monitor.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import py.gov.hospital.quirofanos_monitor.model.Cirugia;
import py.gov.hospital.quirofanos_monitor.model.Quirofano;
import py.gov.hospital.quirofanos_monitor.model.enums.EstadoCirugia;
import py.gov.hospital.quirofanos_monitor.model.enums.EstadoQuirofano;
import py.gov.hospital.quirofanos_monitor.repository.CirugiaRepository;
import py.gov.hospital.quirofanos_monitor.repository.QuirofanoRepository;

@Service
public class CirugiaService {
    private final CirugiaRepository cirugiaRepository; 
    private final QuirofanoRepository quirofanoRepository;

    public CirugiaService(CirugiaRepository cirugiaRepository, QuirofanoRepository quirofanoRepository) {
        this.cirugiaRepository = cirugiaRepository;
        this.quirofanoRepository = quirofanoRepository;
    }

    @Transactional
    public Cirugia iniciarCirugia(Long quirofanoId, String descripcion, Integer duracion) {
        Quirofano q = quirofanoRepository.findById(quirofanoId)
                .orElseThrow(() -> new RuntimeException("Quirofano no encontrado"));
        // Regla hospitalaria CLAVE
        if (q.getEstado() == EstadoQuirofano.OCUPADO) {
                    throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Quirofano no encontrado"
        );
        }

        q.setEstado(EstadoQuirofano.OCUPADO);

        Cirugia c = Cirugia.builder()
                .descripcion(descripcion)
                .horaInicio(LocalDateTime.now())
                .duracionEstimada(duracion)
                .estado(EstadoCirugia.EN_CURSO)
                .quirofano(q)
                .build();
        return cirugiaRepository.save(c);
    }
}
