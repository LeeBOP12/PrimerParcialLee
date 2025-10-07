package pe.edu.upeu.PrimerParcialLee.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.PrimerParcialLee.entity.Cliente;
import pe.edu.upeu.PrimerParcialLee.entity.Participa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProyectoDTO {
    private Long idproyecto;
    private String descripcion;
    private BigDecimal cuantia;
    private LocalDateTime fechainicio;
    private LocalDateTime fechafin;
    private Long idcliente;
    private List<ParticipaDTO> participas;
}
