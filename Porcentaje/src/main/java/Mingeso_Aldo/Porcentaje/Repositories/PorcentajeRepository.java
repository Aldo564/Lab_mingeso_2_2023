package Mingeso_Aldo.Porcentaje.Repositories;

import Mingeso_Aldo.Porcentaje.Entities.PorcentajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PorcentajeRepository extends JpaRepository<PorcentajeEntity, String> {

    @Query("select e from PorcentajeEntity e where e.cod_proveedor = :codigo")
    ArrayList<PorcentajeEntity> findByCodigo(String codigo);

    @Query("select e from PorcentajeEntity e")
    ArrayList<PorcentajeEntity> getAll();

    @Query("select e from PorcentajeEntity e where e.cod_proveedor = :codigo and e.ID_archivo = :id_archivo")
    PorcentajeEntity findFiltro(Integer id_archivo, String codigo);

}
