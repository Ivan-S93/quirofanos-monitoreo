package py.gov.hospital.quirofanos_monitor.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quirofano")
@Data               // Genera los getters, setters, toString, equals y hashCode automaticamente
@NoArgsConstructor  // Constructor sin argumentos obligatorio para JPA
@AllArgsConstructor // Genera el constructor para todos los campos
@Builder            // Permite crear objetos de forma fluida (con patrón builder)
public class Quirofano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "estado", nullable = false)
    private String estado;

}