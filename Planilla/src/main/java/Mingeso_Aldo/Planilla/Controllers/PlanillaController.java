package Mingeso_Aldo.Planilla.Controllers;

import Mingeso_Aldo.Planilla.Entities.PlanillaEntity;
import Mingeso_Aldo.Planilla.Services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/planilla")
public class PlanillaController {

    @Autowired
    PlanillaService planillaService;

    @GetMapping("/generar/{codigo}")
    public ResponseEntity<Boolean> generarPlanilla(Model model, @PathVariable("codigo") String codigo)
    {
        PlanillaEntity planilla = planillaService.generarPlanilla(codigo);
        if (planilla == null)
        {
            System.out.println("Planilla nula");
            ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping("/obtener")
    public ResponseEntity<PlanillaEntity> getAll()
    {
        PlanillaEntity planilla = planillaService.getAll();
        return ResponseEntity.ok(planilla);
    }

    @PostMapping("/eliminar")
    public void eliminarData(@RequestBody ArrayList<PlanillaEntity> planilla)
    {
        planillaService.eliminarData(planilla);
    }
}
