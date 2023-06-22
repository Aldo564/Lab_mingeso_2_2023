package Mingeso_Aldo.Proveedor.Entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProveedorEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_PROVEEDOR;

    private String codigo;
    private String nombre;
    private String categoria;
    private Boolean retencion;
}

