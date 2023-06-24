package Mingeso_Aldo.Proveedor.Controllers;


import Mingeso_Aldo.Proveedor.Entities.ProveedorEntity;
import Mingeso_Aldo.Proveedor.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;


    @GetMapping("/verProveedores")
    public String listar(Model model) {
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        if(proveedores.isEmpty())
        {
            model.addAttribute("noDataMessage", "No existen proveedores en el sistema");
        }
        else
        {
            model.addAttribute("proveedores", proveedores);
        }
        return "verProveedores";
    }

    @GetMapping("/ingresar")
    public String proveedor(){
        return "ingresarProveedor";
    }

    @PostMapping("/ingresar")
    public ResponseEntity<Boolean> nuevoProveedor(@RequestParam("codigo") String codigo,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("categoria") String categoria,
                                 @RequestParam("retencion") Boolean retencion){
        proveedorService.guardarProveedor(codigo, nombre, categoria, retencion);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/findby/{codigo}")
    public ResponseEntity<ProveedorEntity> findByCodigo(@PathVariable("codigo") String codigo)
    {
        ProveedorEntity proveedor = proveedorService.findByCodigo(codigo);
        if (proveedor == null)
        {
            ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(proveedor);
    }

    @GetMapping("/categoria/{codigo}")
    public ResponseEntity<String> obtenerCategoria(@PathVariable("codigo") String codigo)
    {
        String categoria = proveedorService.obtenerCategoria(codigo);
        if (categoria ==  null)
        {
            ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(categoria);
    }

    @GetMapping("/obtener")
    public ResponseEntity<ArrayList<ProveedorEntity>> obtenerProveedores()
    {
        ArrayList<ProveedorEntity> proveedores = new ArrayList<>();
        proveedores = proveedorService.obtenerProveedores();
        /*
        if (proveedores.isEmpty())
        {

            return ResponseEntity.noContent().build();
        }
        */

        return  ResponseEntity.ok(proveedores);
    }
}
