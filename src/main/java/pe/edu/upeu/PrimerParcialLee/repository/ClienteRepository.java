package pe.edu.upeu.PrimerParcialLee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.PrimerParcialLee.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
