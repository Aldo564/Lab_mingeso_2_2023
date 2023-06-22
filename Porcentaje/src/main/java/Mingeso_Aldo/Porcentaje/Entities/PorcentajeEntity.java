package Mingeso_Aldo.Porcentaje.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "porcentaje")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PorcentajeEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_PORCENTAJE;

    private Integer ID_archivo;
    private String cod_proveedor;
    private Integer grasa;
    private Integer solido;
}
