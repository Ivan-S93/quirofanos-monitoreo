package py.gov.hospital.quirofanos_monitor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import py.gov.hospital.quirofanos_monitor.model.enums.EstadoCirugia;

import java.time.LocalDateTime;

@Entity
@Table(name = "cirugia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Cirugia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @NotNull(message = "La hora de inicio es obligatoria")
    @Column(nullable = false)
    private LocalDateTime horaInicio;

    @Positive(message = "La duración debe ser un valor positivo en minutos")
    @Column(nullable = false)
    private Integer duracionEstimada; // en minutos

    private LocalDateTime horaFinReal;

    @NotNull(message = "El estado de la cirugía es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCirugia estado;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy para mejorar el rendimiento
    @JoinColumn(name = "quirofano_id", nullable = false)
    @ToString.Exclude // Evita bucles infinitos en el toString
    private Quirofano quirofano;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cirugia)) return false;
        return id != null && id.equals(((Cirugia) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
