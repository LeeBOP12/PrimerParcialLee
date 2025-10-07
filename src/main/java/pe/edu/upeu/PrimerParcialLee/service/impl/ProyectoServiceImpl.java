package pe.edu.upeu.PrimerParcialLee.service.impl;

import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.PrimerParcialLee.controller.exceptions.ResourceNotFoundException;
import pe.edu.upeu.PrimerParcialLee.controller.exceptions.ResourceValidationException;
import pe.edu.upeu.PrimerParcialLee.dto.ParticipaDTO;
import pe.edu.upeu.PrimerParcialLee.dto.ProyectoDTO;
import pe.edu.upeu.PrimerParcialLee.entity.Cliente;
import pe.edu.upeu.PrimerParcialLee.entity.Colaborador;
import pe.edu.upeu.PrimerParcialLee.entity.Participa;
import pe.edu.upeu.PrimerParcialLee.entity.Proyecto;
import pe.edu.upeu.PrimerParcialLee.mappers.ProyectoMapper;
import pe.edu.upeu.PrimerParcialLee.repository.ClienteRepository;
import pe.edu.upeu.PrimerParcialLee.repository.ColaboradorRepository;
import pe.edu.upeu.PrimerParcialLee.repository.ProyectoRepository;
import pe.edu.upeu.PrimerParcialLee.service.service.ProyectoService;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProyectoServiceImpl implements ProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;
    private final ClienteRepository clienteRepository;
    private final ColaboradorRepository colaboradorRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository, ProyectoMapper proyectoMapper, ClienteRepository clienteRepository, ColaboradorRepository colaboradorRepository) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
        this.clienteRepository = clienteRepository;
        this.colaboradorRepository = colaboradorRepository;
    }
    @Transactional
    @Override
    public ProyectoDTO create(ProyectoDTO proyectoDTO) throws ServiceException {
        if( proyectoDTO == null ) {
            throw new IllegalArgumentException("El proyecto no puede ser nulo.");
        }
        Cliente cliente = clienteRepository.findById(proyectoDTO.getIdcliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + proyectoDTO.getIdcliente()));
        if (proyectoDTO.getParticipas()== null || proyectoDTO.getParticipas().isEmpty()) {
            throw new ResourceValidationException("El proyecto debe tener al menos un colaborador asociado");
        }
        Proyecto proyecto = new Proyecto();
        proyecto.setDescripcion(proyectoDTO.getDescripcion());
        proyecto.setCuantia(proyectoDTO.getCuantia());
        proyecto.setFechainicio(proyectoDTO.getFechainicio());
        proyecto.setFechafin(proyectoDTO.getFechafin());
        proyecto.setCliente(cliente);

        if (proyecto.getFechainicio() != null && proyecto.getFechafin() != null) {
            if (proyecto.getFechafin().isBefore(proyecto.getFechainicio())) {
                throw new ResourceValidationException("La fecha de fin no puede ser anterior a la fecha de inicio");
            }
        }

        List<Participa> participas = new ArrayList<>();
        for (ParticipaDTO p : proyectoDTO.getParticipas()) {
            if (p.getIdcolaborador() == null) {
                throw new ResourceValidationException("El proyecto debe tener 1 colaborador");
            }
            Colaborador colaborador = colaboradorRepository.findById(p.getIdcolaborador())
                    .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con ID: " + p.getIdcolaborador()));

            Participa participa = new Participa();
            participa.setProyecto(proyecto);
            participa.setColaborador(colaborador);

            participas.add(participa);
        }
        proyecto.setParticipas(participas);
        Proyecto guardada = proyectoRepository.save(proyecto);
        return proyectoMapper.toDTO(guardada);
    }
    @Transactional
    @Override
    public ProyectoDTO update(Long aLong, ProyectoDTO proyectoDTO) throws ServiceException {
        if (aLong == null || proyectoDTO == null) {
            throw new IllegalArgumentException("El ID y el proyecto no pueden ser nulos");
        }
        Proyecto proExistente = proyectoRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con ID: " + aLong));

        Cliente cliente = clienteRepository.findById(proyectoDTO.getIdcliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + proyectoDTO.getIdcliente()));

        if (proyectoDTO.getParticipas() == null || proyectoDTO.getParticipas().isEmpty()) {
            throw new ResourceValidationException("El proyecto debe tener al menos un colaborador");
        }
        proExistente.setDescripcion(proyectoDTO.getDescripcion());
        proExistente.setCuantia(proyectoDTO.getCuantia());
        proExistente.setFechainicio(proyectoDTO.getFechainicio());
        proExistente.setFechafin(proyectoDTO.getFechafin());
        proExistente.setCliente(cliente);
        proExistente.getParticipas().clear();

        if (proExistente.getFechainicio() != null && proExistente.getFechafin() != null) {
            if (proExistente.getFechafin().isBefore(proExistente.getFechainicio())) {
                throw new ResourceValidationException("La fecha de fin no puede ser anterior a la fecha de inicio");
            }
        }

        List<Participa> nuevosParticipas = new ArrayList<>();

        for (ParticipaDTO p : proyectoDTO.getParticipas()) {

            if (p.getIdcolaborador() == null) {
                throw new ResourceValidationException("Cada participación debe tener un colaborador válido");
            }


            Colaborador colaborador = colaboradorRepository.findById(p.getIdcolaborador())
                    .orElseThrow(() -> new ResourceNotFoundException("Colaborador no encontrado con ID: " + p.getIdcolaborador()));

            Participa participa = new Participa();
            participa.setProyecto(proExistente);
            participa.setColaborador(colaborador);

            nuevosParticipas.add(participa);
        }

        proExistente.setParticipas(nuevosParticipas);
        Proyecto actualizada = proyectoRepository.save(proExistente);
        return proyectoMapper.toDTO(actualizada);
    }
    @Transactional
    @Override
    public ProyectoDTO findById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        Proyecto venta = proyectoRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrada con ID: " + aLong));

        return proyectoMapper.toDTO(venta);
    }
    @Transactional
    @Override
    public void deleteById(Long aLong) throws ServiceException {
        if (aLong == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        if (!proyectoRepository.existsById(aLong)) {
            throw new ResourceNotFoundException("Proyecto no encontrada con ID: " + aLong);
        }

        try {
            proyectoRepository.deleteById(aLong);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar el proyecto porque tiene colaboradores asociados", ex);
        }
    }
    @Transactional
    @Override
    public List<ProyectoDTO> findAll() throws ServiceException {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return proyectos.stream()
                .map(proyectoMapper::toDTO)
                .toList();
    }
}
