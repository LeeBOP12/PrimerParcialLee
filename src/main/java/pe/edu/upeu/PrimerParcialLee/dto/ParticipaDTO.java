package pe.edu.upeu.PrimerParcialLee.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.PrimerParcialLee.entity.Colaborador;
import pe.edu.upeu.PrimerParcialLee.entity.Proyecto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParticipaDTO {
    private Long idparticipa;
    private Long idproyecto;
    private Long idcolaborador;
}
