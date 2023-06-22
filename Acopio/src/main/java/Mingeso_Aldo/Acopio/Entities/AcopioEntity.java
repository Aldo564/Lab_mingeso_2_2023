package Mingeso_Aldo.Acopio.Entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "acopio")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class AcopioEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_ACOPIO;

    private Integer ID_archivo;
    private String fecha;
    private String turno;
    private String proveedor;
    private Integer kg_leche;
}
