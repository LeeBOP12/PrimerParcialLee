package pe.edu.upeu.PrimerParcialLee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {
    private Long idcliente;
    private String telefone;
    private String domicilio;
    private String razonsocial;
}
