package py.gov.hospital.quirofanos_monitor.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleStatus(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(new ApiError(
            ex.getStatusCode().value(),
            ex.getReason(),
            LocalDateTime.now()
        ));   
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError (
            500,
            "Error interno del Servidor",
            LocalDateTime.now()
        ));
    }

}
