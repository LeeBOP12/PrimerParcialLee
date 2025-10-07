package pe.edu.upeu.PrimerParcialLee.mappers;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.PrimerParcialLee.dto.ParticipaDTO;
import pe.edu.upeu.PrimerParcialLee.entity.Participa;
import pe.edu.upeu.PrimerParcialLee.mappers.base.BaseMappers;

@Mapper(componentModel = "spring")
public interface ParticipaMapper extends BaseMappers<Participa, ParticipaDTO> {
    @Mapping(source = "proyecto.idproyecto", target = "idproyecto")
    @Mapping(source = "colaborador.idcolaborador", target = "idcolaborador")
    ParticipaDTO toDTO(Participa participa);

    @InheritInverseConfiguration
    Participa toEntity(ParticipaDTO participaDTO);
}
