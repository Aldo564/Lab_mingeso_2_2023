package Mingeso_Aldo.Planilla.Repositories;

import Mingeso_Aldo.Planilla.Entities.PlanillaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaRepository extends JpaRepository<PlanillaEntity, String> {

    @Query("select e from PlanillaEntity e")
    PlanillaEntity find();
}
