package Mingeso_Aldo.Acopio.Controllers;

import Mingeso_Aldo.Acopio.Services.AcopioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@RestController
@RequestMapping("/acopio")
public class AcopioController {

    private AcopioService acopioService;

    @GetMapping("/subirArchivoAcopio")
    public String mainAcopio() {
        return "subirArchivoAcopio";
    }

    @PostMapping("/subirArchivoAcopio")
    public String uploadAcopio(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        acopioService.guardar(file);
        boolean correcto = acopioService.leerCsvAcopio(file.getOriginalFilename());
        if (correcto)
        {
            redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        }
        else
        {
            redirectAttributes.addFlashAttribute("mensaje", "Verifique el archivo que esta subiendo");
        }

        return "redirect:/subirArchivoAcopio";
    }

    @GetMapping("/fechas/{codigo}")
    public ResponseEntity<ArrayList<String>> obtenerFechasAcopio(@PathVariable("codigo") String codigo)
    {
        ArrayList<String> fechas = acopioService.obtenerFechas(codigo);
        if(fechas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fechas);
    }

    @GetMapping("/kls/{codigo}")
    public ResponseEntity<Integer> obtenerKlsLeche(@PathVariable("codigo") String codigo)
    {
        int kls = acopioService.kgs_leche(codigo); //Como minimo entrega 0
        return ResponseEntity.ok(kls);
    }

    @GetMapping("/turnos/{codigo}")
    public ResponseEntity<ArrayList<Boolean>> obtenerTurnos(@PathVariable("codigo") String codigo)
    {
        ArrayList<Boolean> turnos = acopioService.obtenerTurnos(codigo);
        if(turnos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/variacionLeche/{codigo}")
    public ResponseEntity<ArrayList<String>> obtenerVariacionLeche(@PathVariable("codigo") String codigo)
    {
        ArrayList<String> variacionLeche = acopioService.obtenerVariacionLeche(codigo);
        if(variacionLeche.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(variacionLeche);
    }

}
