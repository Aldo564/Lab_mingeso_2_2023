package Mingeso_Aldo.Porcentaje.Controllers;

import Mingeso_Aldo.Porcentaje.Services.PorcentajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/porcentaje")
public class PorcentajeController {


    @Autowired
    private PorcentajeService porcentajeService;

    @PostMapping
    public ResponseEntity<Boolean> uploadPorcentaje(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        porcentajeService.guardar(file);
        boolean correcto = porcentajeService.leerCsvPorcentaje(file.getOriginalFilename());
        if (correcto)
        {
            redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        }
        else
        {
            redirectAttributes.addFlashAttribute("mensaje", "Verifique el archivo que esta subiendo");
        }

        return ResponseEntity.ok(correcto);
    }

    @GetMapping("/grasa/{codigo}")
    public ResponseEntity<Integer> obtenerGrasa(@PathVariable("codigo") String codigo)
    {
        int grasa = porcentajeService.obtenerGrasa(codigo);
        if(grasa == -1)
        {
            ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(grasa);
    }

    @GetMapping("/solido/{codigo}")
    public ResponseEntity<Integer> obtenerSolido(@PathVariable("codigo") String codigo)
    {
        int solido = porcentajeService.obtenerSolido(codigo);
        if(solido == -1)
        {
            ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(solido);
    }

    @GetMapping("/difGrasa/{codigo}")
    public ResponseEntity<String> obtenerDiferencaGrasa(@PathVariable("codigo") String codigo)
    {
        String difGrasa = porcentajeService.obtenerDiferenciaGrasa(codigo);
        if (difGrasa.equals("0"))
        {
            ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(difGrasa);
    }

    @GetMapping("/difSolido/{codigo}")
    public ResponseEntity<String> obtenerDiferenciaSolido(@PathVariable("codigo") String codigo)
    {
        String difSolido = porcentajeService.obtenerDiferenciaSolido(codigo);
        if (difSolido.equals("0"))
        {
            ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(difSolido);
    }

}
