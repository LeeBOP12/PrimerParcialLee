package pe.edu.upeu.PrimerParcialLee.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PROYECTO")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPROYECTO")
    private Long idproyecto;

    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "CUANTIA", nullable = false)
    private BigDecimal cuantia;

    @Column(name = "FECHA_INICIO", nullable = false, length = 150)
    private LocalDateTime fechainicio;

    @Column(name = "FECHA_FIN", nullable = false, length = 15)
    private LocalDateTime fechafin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDCLIENTE")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "proyecto")
    private List<Participa> participas;
}
