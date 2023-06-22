package Mingeso_Aldo.Planilla.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AcopioModel {

    private String fecha;
    private String turno;
    private String proveedor;
    private Integer kg_leche;
}
