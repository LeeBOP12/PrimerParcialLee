package pe.edu.upeu.PrimerParcialLee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.PrimerParcialLee.entity.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}
