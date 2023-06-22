package Mingeso_Aldo.Planilla.Entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "planilla")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanillaEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_PLANILLA;

    private String fecha;
    private String codigo;
    private String nombre;
    private String kg_leche;
    private String dias;
    private String promedio_kg;
    private String variacion_leche;
    private String grasa;
    private String variacion_grasa;
    private String solidos;
    private String variacion_st;
    private String pago_leche;
    private String pago_grasa;
    private String pago_solidos;
    private String bonif_freq;
    private String dct_variacion_leche;
    private String dct_variacion_grasa;
    private String dct_variacion_st;
    private String pago_total;
    private String monto_retencion;
    private String monto_final;
}
