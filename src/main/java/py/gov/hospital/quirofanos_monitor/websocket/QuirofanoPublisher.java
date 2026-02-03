package py.gov.hospital.quirofanos_monitor.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import py.gov.hospital.quirofanos_monitor.dto.QuirofanoEstadoDTO;
import py.gov.hospital.quirofanos_monitor.service.QuirofanoService;

import java.util.List;

@Component
public class QuirofanoPublisher {

    private final SimpMessagingTemplate messagingTemplate;
    private final QuirofanoService quirofanoService;

    public QuirofanoPublisher(SimpMessagingTemplate messagingTemplate,
                              QuirofanoService quirofanoService) {
        this.messagingTemplate = messagingTemplate;
        this.quirofanoService = quirofanoService;
    }

    public void publicarEstadoActual() {
        List<QuirofanoEstadoDTO> data = quirofanoService.obtenerEstadoActual();
        messagingTemplate.convertAndSend("/topic/quirofanos", data);
    }
}