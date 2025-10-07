package pe.edu.upeu.PrimerParcialLee.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.PrimerParcialLee.dto.ColaboradorDTO;
import pe.edu.upeu.PrimerParcialLee.entity.Colaborador;
import pe.edu.upeu.PrimerParcialLee.mappers.base.BaseMappers;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper extends BaseMappers<Colaborador, ColaboradorDTO> {
}
