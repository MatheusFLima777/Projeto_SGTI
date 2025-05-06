package com.matheus.HelpDesk.Services;

import com.matheus.HelpDesk.Repository.AgenteRepository;
import com.matheus.HelpDesk.Resources.execption.ObjNotFoundException;
import com.matheus.HelpDesk.domain.Agente;
import com.matheus.HelpDesk.domain.DTOS.AgenteDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteService {

    private final AgenteRepository agenteRepository;

    public AgenteService(AgenteRepository Repository) {
        this.agenteRepository = Repository;
    }

    public Agente findById(Integer id) {
        Optional<Agente> obj = agenteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Agente não encontrado com ID: "+ id));
    }

    public List<Agente> findAll() {
        return agenteRepository.findAll();
    }

    public Agente create(AgenteDTO objDTO){
        objDTO.setId(null);
        Agente newObj = new Agente(objDTO);
        return agenteRepository.save(newObj);
    }
}
