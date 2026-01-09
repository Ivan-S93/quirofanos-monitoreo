package py.gov.hospital.quirofanos_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.gov.hospital.quirofanos_monitor.model.Quirofano;
import py.gov.hospital.quirofanos_monitor.model.enums.EstadoQuirofano;
import java.util.List;

public interface QuirofanoRepository extends JpaRepository<Quirofano, Long> {
    
    // Para mostrar solo quirófanos operativos en el monitor
    List<Quirofano> findByEstadoNot(EstadoQuirofano estado);
}