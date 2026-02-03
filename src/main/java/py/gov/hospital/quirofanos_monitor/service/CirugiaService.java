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
import py.gov.hospital.quirofanos_monitor.websocket.QuirofanoPublisher;

@Service
public class CirugiaService {
    private final CirugiaRepository cirugiaRepository; 
    private final QuirofanoRepository quirofanoRepository;
    private final QuirofanoPublisher publisher;

    public CirugiaService(CirugiaRepository cirugiaRepository, QuirofanoRepository quirofanoRepository, QuirofanoPublisher publisher) {
        this.cirugiaRepository = cirugiaRepository;
        this.quirofanoRepository = quirofanoRepository;
        this.publisher = publisher;
    }

    //---------INICIAR CIRUGIA ----------//
    @Transactional
    public Cirugia iniciarCirugia(Long quirofanoId, String descripcion, Integer duracion) {
        Quirofano q = quirofanoRepository.findById(quirofanoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Quirofano no encontrado"));
        // Regla hospitalaria CLAVE no se puede usar si esta ocupado
        if (q.getEstado() == EstadoQuirofano.OCUPADO) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El quirofano ya está ocupado");
        }

        q.setEstado(EstadoQuirofano.OCUPADO);
        quirofanoRepository.save(q);

        Cirugia c = Cirugia.builder()
                .descripcion(descripcion)
                .horaInicio(LocalDateTime.now())
                .duracionEstimada(duracion)
                .estado(EstadoCirugia.EN_CURSO)
                .quirofano(q)
                .build();
        
        Cirugia guardada = cirugiaRepository.save(c);
        // notificar a todos los clientes conectados sobre el cambio de estado
        publisher.publicarEstadoActual();
        return guardada;
    }

    // ---------FINALIZAR CIRUGIA ----------//

    @Transactional
    public Cirugia finalizarCirugia(Long quirofanoId) {

        Cirugia cirugia = cirugiaRepository.findByQuirofanoIdAndEstado(quirofanoId, EstadoCirugia.EN_CURSO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "No hay cirugía en curso en este quirofano"));
        cirugia.setEstado(EstadoCirugia.FINALIZADA);
        cirugia.setHoraFinReal(LocalDateTime.now());

        //Liberar quirofano
        Quirofano q = cirugia.getQuirofano();
        q.setEstado(EstadoQuirofano.DISPONIBLE);

        quirofanoRepository.save(q);
        Cirugia guardada = cirugiaRepository.save(cirugia);

        // notificar a todos los clientes conectados sobre el cambio de estado
        publisher.publicarEstadoActual();
        return guardada;
    }
}
