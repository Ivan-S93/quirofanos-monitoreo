package py.gov.hospital.quirofanos_monitor.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import py.gov.hospital.quirofanos_monitor.model.Quirofano;
import py.gov.hospital.quirofanos_monitor.model.enums.EstadoQuirofano;
import py.gov.hospital.quirofanos_monitor.repository.QuirofanoRepository;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner initQuirofanos (QuirofanoRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(Quirofano.builder().nombre("Quirofano 1").tipo("General").estado(EstadoQuirofano.DISPONIBLE).build());
                repo.save(Quirofano.builder().nombre("Quirofano 2").tipo("General").estado(EstadoQuirofano.DISPONIBLE).build());
                repo.save(Quirofano.builder().nombre("Quirofano 3").tipo("General").estado(EstadoQuirofano.DISPONIBLE).build());
                repo.save(Quirofano.builder().nombre("Quirofano 4").tipo("General").estado(EstadoQuirofano.DISPONIBLE).build());
                repo.save(Quirofano.builder().nombre("Quirofano 5").tipo("General").estado(EstadoQuirofano.DISPONIBLE).build());

            }
        };
    }
}
