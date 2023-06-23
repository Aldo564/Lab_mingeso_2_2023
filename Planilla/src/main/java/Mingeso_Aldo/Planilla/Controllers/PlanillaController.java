package Mingeso_Aldo.Planilla.Controllers;

import Mingeso_Aldo.Planilla.Entities.PlanillaEntity;
import Mingeso_Aldo.Planilla.Models.ProveedorModel;
import Mingeso_Aldo.Planilla.Services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planilla")
public class PlanillaController {

    @Autowired
    PlanillaService planillaService;

    @GetMapping("/seleccionarPlanilla")
    public String main(Model model){
        List<ProveedorModel> proveedores = planillaService.obtenerProveedores();

        if (proveedores.isEmpty())
        {
            model.addAttribute("NoProveedores", "No Existen proveedores ingresados en el sistema.");
        }
        else
        {
            model.addAttribute("proveedores", proveedores);
        }
        return "seleccionarPlanilla";
    }

    @GetMapping("/generarPlanilla")
    public String generarPlanilla(Model model, @RequestParam("codigo") String codigo)
    {
        PlanillaEntity planilla = planillaService.generarPlanilla(codigo);
        if (planilla == null)
        {
            model.addAttribute("NoDataMessage", "No existen pagos asociados a este codigo de proveedor");
        }
        else
        {
            model.addAttribute("planilla", planilla);
        }
        return "mostrarPlanilla";
    }

    @GetMapping("/obtener")
    public ResponseEntity<PlanillaEntity> getAll()
    {
        PlanillaEntity planilla = planillaService.getAll();
        if (planilla == null)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(planilla);
    }

    @PostMapping("/eliminar")
    public void eliminarData(@RequestBody ArrayList<PlanillaEntity> planilla)
    {
        planillaService.eliminarData(planilla);
    }
}
