package Mingeso_Aldo.Proveedor.Repositories;

import Mingeso_Aldo.Proveedor.Entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, String> {

    @Query("select e.categoria from ProveedorEntity e where e.codigo = :codigo")
    String findCategory(@Param("codigo") String codigo);

    @Query("select e from ProveedorEntity e where e.codigo = :codigo")
    ProveedorEntity findByCodigo(@Param("codigo")String codigo);


}
