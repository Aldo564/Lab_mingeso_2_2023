package Mingeso_Aldo.Planilla.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProveedorModel {

    private String codigo;
    private String nombre;
    private String categoria;
    private Boolean retencion;
}

