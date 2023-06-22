package Mingeso_Aldo.Planilla.Services;

import Mingeso_Aldo.Planilla.Entities.PlanillaEntity;
import Mingeso_Aldo.Planilla.Models.ProveedorModel;
import Mingeso_Aldo.Planilla.Repositories.PlanillaRepository;
import com.sun.istack.NotNull;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanillaService {

    @Autowired
    PlanillaRepository planillaRepository;

    @Autowired
    private PlanillaService planillaService;

    @Autowired
    RestTemplate restTemplate;

    public void guardarPlanilla(String fecha, String codigo, String nombre, String kg_leche,
                                String dias, String promedio_kg, String variacion_leche,
                                String grasa, String variacion_grasa,
                                String solidos, String variacion_st, String pago_leche,
                                String pago_grasa, String pago_solidos, String bonif_freq,
                                String dct_variacion_leche, String dct_variacion_grasa,
                                String dct_Variacion_st, String pago_total, String monto_retencion,
                                String monto_final)
    {
        PlanillaEntity planilla = new PlanillaEntity();
        planilla.setFecha(fecha);
        planilla.setCodigo(codigo);
        planilla.setNombre(nombre);
        planilla.setKg_leche(kg_leche);
        planilla.setDias(dias);
        planilla.setPromedio_kg(promedio_kg);
        planilla.setVariacion_leche(variacion_leche);
        planilla.setGrasa(grasa);
        planilla.setVariacion_grasa(variacion_grasa);
        planilla.setSolidos(solidos);
        planilla.setVariacion_st(variacion_st);
        planilla.setPago_leche(pago_leche);
        planilla.setPago_grasa(pago_grasa);
        planilla.setPago_solidos(pago_solidos);
        planilla.setBonif_freq(bonif_freq);
        planilla.setDct_variacion_leche(dct_variacion_leche);
        planilla.setDct_variacion_grasa(dct_variacion_grasa);
        planilla.setDct_variacion_st(dct_Variacion_st);
        planilla.setPago_total(pago_total);
        planilla.setMonto_retencion(monto_retencion);
        planilla.setMonto_final(monto_final);
        planillaRepository.save(planilla);
    }

    public PlanillaEntity getAll()
    {
        return planillaRepository.find();
    }

    @Generated
    public void eliminarData(ArrayList<PlanillaEntity> datas)
    {
        planillaRepository.deleteAll(datas);
    }

    public int pagoPorcentajeSolido(int solido, int kilos)
    {
        if(0 <= solido && solido <= 7)
        {
            return (-130 * kilos);
        }
        else if(8 <= solido && solido <= 18)
        {
            return (-90 * kilos);
        }
        else if(19 <= solido && solido <= 35)
        {
            return (95 * kilos);
        }
        else if (36 <= solido)
        {
            return (150 * kilos);
        }
        else
        {
            return -1;
        }
    }

    public int pagoPorcentajeGrasa(int grasa, int kilos)
    {
        if(0 <= grasa && grasa <= 20)
        {
            return (30 * kilos);
        }
        else if(21 <= grasa && grasa <= 45)
        {
            return (80 * kilos);
        }
        else if(46 <= grasa)
        {
            return (120 * kilos);
        }
        else
        {
            return -1;
        }
    }

    public int pagoCategoria(@NotNull String categoria, int kilos)
    {
        return switch (categoria) {
            case "A" -> (700 * kilos);
            case "B" -> (550 * kilos);
            case "C" -> (400 * kilos);
            case "D" -> (250 * kilos);
            default -> -1;
        };
    }

    public int variacionKilos(int dif_kilos)
    {
        if(0 <= dif_kilos && dif_kilos <= 8)
        {
            return 0;
        }
        else if(9 <= dif_kilos && dif_kilos <= 25)
        {
            return 7;
        }
        else if(26 <= dif_kilos && dif_kilos <= 45)
        {
            return 15;
        }
        else if(46 <= dif_kilos)
        {
            return 30;
        }
        else
        {
            return -1;
        }
    }

    public int variacionGrasa(int dif_grasa)
    {
        if(0 <= dif_grasa && dif_grasa <= 15)
        {
            return 0;
        }
        else if(16 <= dif_grasa && dif_grasa <= 25)
        {
            return 12;
        }
        else if(26 <= dif_grasa && dif_grasa <= 40)
        {
            return 20;
        }
        else if(41 <= dif_grasa)
        {
            return 30;
        }
        else
        {
            return -1;
        }
    }

    public int variacionSolido(int dif_solido)
    {
        if(0 <= dif_solido && dif_solido <= 6)
        {
            return 0;
        }
        else if(7 <= dif_solido && dif_solido <= 12)
        {
            return 18;
        }
        else if(13 <= dif_solido && dif_solido <= 35)
        {
            return 27;
        }
        else if(36 <= dif_solido)
        {
            return 45;
        }
        else
        {
            return -1;
        }
    }

    public double pagoFrecuencia(double pagoAcopio, boolean maniana, boolean tarde)
    {
        double pagoFrecuencia = 0;
        if (maniana && tarde)
        {
            pagoFrecuencia = pagoAcopio*0.2;
        }
        else if (maniana)
        {
            pagoFrecuencia =  pagoAcopio*0.12;
        }
        else if (tarde)
        {
            pagoFrecuencia = pagoAcopio*0.08;
        }

        return pagoFrecuencia;
    }

    public String obtenerDias(@NotNull List<String> fechas)
    {
        ArrayList<String> dias = new ArrayList<>();
        for (String fecha:fechas)
        {
            System.out.println(fechas);
            String aux = fecha.split("/")[0];
            if (!dias.contains(aux))
            {
                dias.add(aux);
            }
        }

        return (Integer.toString(dias.size()));
    }

    public List<String> obtenerFechasAcopio(String codigo) {
        List<String> fechasAcopio = restTemplate.getForObject("http://acopio-service/acopio/fechas/" + codigo, List.class);
        return fechasAcopio;
    }

    public int obtenerKlsLeche(String codigo) {
        int kls = restTemplate.getForObject("http://acopio-service/acopio/kls/" + codigo, Integer.class);
        return kls;
    }

    public List<Boolean> obtenerTurnos(String codigo) {
        List<Boolean> turnos = restTemplate.getForObject("http://acopio-service/acopio/turnos/" + codigo, List.class);
        return turnos;
    }

    public List<String> obtenerVariacionLeche(String codigo) {
        List<String> variacionLeche = restTemplate.getForObject("http://acopio-service/acopio/variacionLeche/" + codigo, List.class);
        return variacionLeche;
    }

    public ProveedorModel findByCodigo(String codigo) {
        ProveedorModel proveedor = restTemplate.getForObject("http://proveedor-service/proveedor/findBy/" + codigo, ProveedorModel.class);
        return proveedor;
    }

    public String obtenerCategoria(String codigo) {
        String proveedor = restTemplate.getForObject("http://proveedor-service/proveedor/categoria/" + codigo, String.class);
        return proveedor;
    }

    public int obtenerGrasa(String codigo) {
        int grasa = restTemplate.getForObject("http://porcentaje-service/porcentaje/grasa/" + codigo, Integer.class);
        return grasa;
    }

    public int obtenerSolido(String codigo) {
        int solido = restTemplate.getForObject("http://porcentaje-service/porcentaje/solido/" + codigo, Integer.class);
        return solido;
    }

    public String obtenerDiferenciaGrasa(String codigo) {
        String difGrasa = restTemplate.getForObject("http://porcentaje-service/porcentaje/difGrasa/" + codigo, String.class);
        return difGrasa;
    }

    public String obtenerDiferenciaSolido(String codigo) {
        String difSolido = restTemplate.getForObject("http://porcentaje-service/porcentaje/difSolido/" + codigo, String.class);
        return difSolido;
    }

    public PlanillaEntity generarPlanilla(String codigo) {
        List<String> fechas = obtenerFechasAcopio(codigo);
        ProveedorModel proveedor = findByCodigo(codigo);

        List<String> variacion_leche = obtenerVariacionLeche(codigo);
        String nombre = proveedor.getNombre();
        int kgs_leche = obtenerKlsLeche(codigo);

        String dias = obtenerDias(fechas);

        String quincena = "";
        if (Integer.parseInt(fechas.get(0).split("/")[0]) <= 15) {
            quincena = "1";
        } else {
            quincena = "2";
        }

        int dif_kilos = Integer.parseInt(variacion_leche.get(0));
        String categoria = obtenerCategoria(codigo);
        int grasa = obtenerGrasa(codigo);
        int solido = obtenerSolido(codigo);
        List<Boolean> turnos = obtenerTurnos(codigo);
        boolean maniana = turnos.get(0);
        boolean tarde = turnos.get(1);
        String dif_grasa = obtenerDiferenciaGrasa(codigo);
        String dif_solido = obtenerDiferenciaSolido(codigo);

        int pago_Leche = pagoCategoria(categoria, kgs_leche);
        int pago_Grasa = pagoPorcentajeGrasa(grasa, kgs_leche);
        int pago_Solidos = pagoPorcentajeSolido(solido, kgs_leche);


        double pago_Acopio = pago_Leche + pago_Grasa + pago_Solidos;

        double pago_Frecuencia = 0.00;
        if (Integer.parseInt(dias) >= 10)
        {
            pago_Frecuencia = pagoFrecuencia(pago_Acopio,maniana, tarde);
        }

        pago_Acopio = pago_Acopio + pago_Frecuencia;

        double var_kg = 0;
        if (dif_kilos < 0)
        {
            var_kg = pago_Acopio * (variacionKilos(Math.abs(dif_kilos)/100));
        }

        double var_grasa = pago_Acopio * (variacionGrasa(Integer.parseInt(dif_grasa))/100.0);
        double var_solido = pago_Acopio * (variacionSolido(Integer.parseInt(dif_solido))/100.0);
        double descuentos = var_kg + var_grasa + var_solido;
        double pago_total = pago_Acopio - descuentos;

        double retencion = 0.00;
        if (pago_total >= 950000 && proveedor.getRetencion())
        {
            retencion = pago_total*0.13;
        }

        double pago_final = pago_total - retencion;

        planillaService.guardarPlanilla(quincena, codigo, nombre, Integer.toString(kgs_leche), dias,
                Integer.toString(kgs_leche/Integer.parseInt(dias)), Double.toString(var_kg), Integer.toString(grasa), dif_grasa,
                Integer.toString(solido), dif_solido, Integer.toString(pago_Leche), Integer.toString(pago_Grasa),
                Integer.toString(pago_Solidos), Double.toString(pago_Frecuencia), Double.toString(var_kg),
                Double.toString(var_grasa), Double.toString(var_solido), Double.toString(pago_total),
                Double.toString(retencion), Double.toString(pago_final));

        PlanillaEntity planilla = planillaService.getAll();

        return planilla;

    }

    public List<ProveedorModel> obtenerProveedores()
    {
        List<ProveedorModel> proveedores = restTemplate.getForObject("http://proveedor-service/proveedor/obtener/", List.class);
        return proveedores;
    }

}
