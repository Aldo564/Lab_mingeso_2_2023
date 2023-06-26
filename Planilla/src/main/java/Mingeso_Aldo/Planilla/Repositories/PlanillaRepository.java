package Mingeso_Aldo.Planilla.Repositories;

import Mingeso_Aldo.Planilla.Entities.PlanillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PlanillaRepository extends JpaRepository<PlanillaEntity, String> {

    @Query(value = "select * from planilla e ORDER BY e.ID_PLANILLA DESC LIMIT 1", nativeQuery = true)
    ArrayList<PlanillaEntity> find();
}
