package py.gov.hospital.quirofanos_monitor.websocket;

import java.util.List;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import py.gov.hospital.quirofanos_monitor.dto.QuirofanoEstadoDTO;
import py.gov.hospital.quirofanos_monitor.service.QuirofanoService;

@Controller
public class QuirofanoControllerWS {
    
    private final QuirofanoService quirofanoService;

    public QuirofanoControllerWS(QuirofanoService quirofanoService) {
        this.quirofanoService = quirofanoService;
    }

    @SendTo("/topic/quirofanos")
    public List<QuirofanoEstadoDTO> enviarEstado() {
        return quirofanoService.obtenerEstadoActual();
    }
}
