package Mingeso_Aldo.Porcentaje.Controllers;

import Mingeso_Aldo.Porcentaje.Services.PorcentajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/porcentaje")
public class PorcentajeController {


    private PorcentajeService porcentajeService;


    @GetMapping("/subirArchivoPorcentaje")
    public String mainPorcentaje() {
        return "subirArchivoPorcentaje";
    }

    @PostMapping("/subirArchivoPorcentaje")
    public String uploadPorcentaje(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
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

        return "redirect:/subirArchivoPorcentaje";
    }

    @GetMapping("/grasa/{codigo}")
    public ResponseEntity<Integer> obtenerCategoria(@PathVariable("codigo") String codigo)
    {
        Integer grasa = porcentajeService.obtenerGrasa(codigo);
        return  ResponseEntity.ok(grasa);
    }

    @GetMapping("/solido/{codigo}")
    public ResponseEntity<Integer> obtenerSolido(@PathVariable("codigo") String codigo)
    {
        Integer solido = porcentajeService.obtenerSolido(codigo);
        return  ResponseEntity.ok(solido);
    }

    @GetMapping("/diGrasa/{codigo}")
    public ResponseEntity<String> obtenerDiferencaGrasa(@PathVariable("codigo") String codigo)
    {
        String difGrasa = porcentajeService.obtenerDiferenciaGrasa(codigo);
        return  ResponseEntity.ok(difGrasa);
    }

    @GetMapping("/difSolido/{codigo}")
    public ResponseEntity<String> obtenerDiferenciaSolido(@PathVariable("codigo") String codigo)
    {
        String difSolido = porcentajeService.obtenerDiferenciaSolido(codigo);
        return  ResponseEntity.ok(difSolido);
    }

}
