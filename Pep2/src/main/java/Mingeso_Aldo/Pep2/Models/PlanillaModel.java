package Mingeso_Aldo.Pep2.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanillaModel {


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
