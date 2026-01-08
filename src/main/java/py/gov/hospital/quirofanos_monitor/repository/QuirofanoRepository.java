package py.gov.hospital.quirofanos_monitor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import py.gov.hospital.quirofanos_monitor.model.Quirofano;

public interface QuirofanoRepository extends JpaRepository<Quirofano, Long> {
    
}
