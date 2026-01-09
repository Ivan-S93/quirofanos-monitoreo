package py.gov.hospital.quirofanos_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.gov.hospital.quirofanos_monitor.model.Cirugia;
import java.util.Optional;

public interface CirugiaRepository extends JpaRepository<Cirugia, Long> {
    //  Metodo perzonalizado para encontrar la cirugia activa en un quirofano especifico
    // Este es importante para que pintar el cuadro en rojo en el frontend
    Optional<Cirugia> findByQuirofanoIdAndEstado(Long quirofanoId, String estado);
}