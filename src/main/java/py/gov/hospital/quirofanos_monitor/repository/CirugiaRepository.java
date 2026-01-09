package py.gov.hospital.quirofanos_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.gov.hospital.quirofanos_monitor.model.Cirugia;
import py.gov.hospital.quirofanos_monitor.model.enums.EstadoCirugia; // Importa el Enum
import java.util.Optional;
import java.util.List;

public interface CirugiaRepository extends JpaRepository<Cirugia, Long> {
    
    // Cambiamos String por EstadoCirugia
    Optional<Cirugia> findByQuirofanoIdAndEstado(Long quirofanoId, EstadoCirugia estado);

    // Tip Pro: Buscar todas las cirugías activas (útil para el monitor general)
    List<Cirugia> findByEstado(EstadoCirugia estado);
}