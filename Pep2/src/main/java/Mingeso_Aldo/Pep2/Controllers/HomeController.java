package Mingeso_Aldo.Pep2.Controllers;

import Mingeso_Aldo.Pep2.Models.PlanillaModel;
import Mingeso_Aldo.Pep2.Services.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping
public class HomeController {

    HomeService homeService;

    @GetMapping("/")
    public String main()
    {
        PlanillaModel planilla = homeService.getAll();
        if (planilla != null)
        {
            ArrayList<PlanillaModel> aux = new ArrayList<>();
            aux.add(planilla);
            homeService.eliminarData(aux);
        }
        return "home";
    }
}
