package py.gov.hospital.quirofanos_monitor.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.gov.hospital.quirofanos_monitor.model.Cirugia;
import py.gov.hospital.quirofanos_monitor.service.CirugiaService;

@RestController
@RequestMapping("/api/cirugias")

public class CirugiaController {
    private final CirugiaService service;

    public CirugiaController(CirugiaService service) {
        this.service = service;
    }

    @PostMapping("/iniciar/{quirofanoId}")
    public Cirugia iniciar(
            @PathVariable Long quirofanoId,
            @RequestParam String descripcion,
            @RequestParam Integer duracion) {

                return service.iniciarCirugia(quirofanoId, descripcion, duracion);
            }
    
}
