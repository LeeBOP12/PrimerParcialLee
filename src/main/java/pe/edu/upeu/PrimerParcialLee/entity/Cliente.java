package pe.edu.upeu.PrimerParcialLee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCLIENTE")
    private Long idcliente;

    @Column(name = "TELEFONO", nullable = false, length = 15)
    private String telefono;

    @Column(name = "DOMICILIO", nullable = false, length = 150)
    private String domicilio;

    @Column(name = "RAZONSOCIAL", nullable = false, length = 200)
    private String razonsocial;
}
