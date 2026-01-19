package py.gov.hospital.quirofanos_monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import py.gov.hospital.quirofanos_monitor.model.Quirofano;
import py.gov.hospital.quirofanos_monitor.repository.QuirofanoRepository;

@Service
public class QuirofanoService {
    private final QuirofanoRepository quirofanoRepository;

    public QuirofanoService(QuirofanoRepository quirofanoRepository) {
        this.quirofanoRepository = quirofanoRepository;
    }

    /** 
     * Devuelve el estado actual de todos los quirofanos.
     * El frontend usara esto para pintar las tarjetas.
     */
    public List<Quirofano> obtenerEstadoActual() {
        return quirofanoRepository.findAll();
    }
}
