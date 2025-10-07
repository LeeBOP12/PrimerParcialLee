package pe.edu.upeu.PrimerParcialLee.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.PrimerParcialLee.dto.ProyectoDTO;
import pe.edu.upeu.PrimerParcialLee.entity.Proyecto;
import pe.edu.upeu.PrimerParcialLee.mappers.base.BaseMappers;

@Mapper(componentModel = "spring", uses = ParticipaMapper.class)
public interface ProyectoMapper extends BaseMappers<Proyecto, ProyectoDTO> {
    @Mapping(source = "cliente.idcliente", target = "idcliente")
    @Mapping(source = "participas", target = "participas")
    ProyectoDTO toDTO(Proyecto proyecto);

    @InheritInverseConfiguration
    Proyecto toEntity(ProyectoDTO proyectoDTO);
}
