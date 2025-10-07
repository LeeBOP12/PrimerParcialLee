package pe.edu.upeu.PrimerParcialLee.mappers;


import org.mapstruct.Mapper;
import pe.edu.upeu.PrimerParcialLee.dto.ClienteDTO;
import pe.edu.upeu.PrimerParcialLee.entity.Cliente;
import pe.edu.upeu.PrimerParcialLee.mappers.base.BaseMappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMappers<Cliente, ClienteDTO> {
}
