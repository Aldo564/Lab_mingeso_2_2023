package Mingeso_Aldo.Pep2.Services;

import Mingeso_Aldo.Pep2.Models.PlanillaModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class HomeService {

    RestTemplate restTemplate;

    public PlanillaModel getAll()
    {
        PlanillaModel planilla = restTemplate.getForObject("http://planilla-service/planilla/obtener/", PlanillaModel.class);
        return planilla;
    }

    public void eliminarData(ArrayList<PlanillaModel> planilla)
    {
        restTemplate.getForObject("http://planilla-service/planilla/eliminar", PlanillaModel.class);
    }
}
