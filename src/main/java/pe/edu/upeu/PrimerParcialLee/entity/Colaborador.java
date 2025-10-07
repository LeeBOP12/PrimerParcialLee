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
@Table(name="COLABORADOR")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCOLABORADOR")
    private Long idcolaborador;

    @Column(name = "NIF", nullable = false, length = 15)
    private String nif;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DOMICILIO", nullable = false, length = 150)
    private String domicilio;

    @Column(name = "TELEFONO", nullable = false, length = 15)
    private String telefono;

    @Column(name = "BANCO", nullable = false, length = 100)
    private String banco;

    @Column(name = "NUM_CUENTA", nullable = false, length = 30)
    private String numcuenta;
}
