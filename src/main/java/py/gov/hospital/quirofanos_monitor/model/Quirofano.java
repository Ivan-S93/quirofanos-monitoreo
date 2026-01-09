package py.gov.hospital.quirofanos_monitor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import py.gov.hospital.quirofanos_monitor.model.enums.EstadoQuirofano;

@Entity
@Table(name = "quirofano")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Quirofano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del quirófano es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(nullable = false, length = 100)
    private String tipo;

    @NotNull(message = "El estado no puede estar vacio")
    @Enumerated(EnumType.STRING) //  se guarda como string en la BD 
    @Column(nullable = false)
    private EstadoQuirofano estado;

    // Custom equals y hashCode usando solo el ID para estabilidad en colecciones JPA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quirofano)) return false;
        return id != null && id.equals(((Quirofano) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}