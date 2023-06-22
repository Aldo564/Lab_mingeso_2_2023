package Mingeso_Aldo.Acopio.Repositories;

import Mingeso_Aldo.Acopio.Entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity, String> {

    @Query("select e from AcopioEntity e where e.proveedor = :codigo")
    ArrayList<AcopioEntity> findByCodigo(@Param("codigo")String codigo);

    @Query("select e from AcopioEntity e")
    ArrayList<AcopioEntity> getAll();

    @Query("select e from AcopioEntity e where e.proveedor = :codigo and e.ID_archivo = :id_archivo")
    ArrayList<AcopioEntity> findFiltro(Integer id_archivo, String codigo);

    @Query("select e.fecha from AcopioEntity e where e.ID_archivo = :id_archivo and e.proveedor = :codigo")
    ArrayList<String> fechasFiltro(Integer id_archivo, String codigo);

    @Query("select e.kg_leche from AcopioEntity e where e.proveedor = :codigo and e.ID_archivo = :id_archivo")
    ArrayList<Integer> kgsFiltro(Integer id_archivo, String codigo);

    @Query("select e.turno from AcopioEntity e where e.proveedor = :codigo and e.ID_archivo = :id_archivo")
    ArrayList<String> findTurnos(Integer id_archivo, String codigo);

}
