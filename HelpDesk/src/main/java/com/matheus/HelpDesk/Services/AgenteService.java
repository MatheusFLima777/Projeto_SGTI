package com.matheus.HelpDesk.Services;

import com.matheus.HelpDesk.Repository.AgenteRepository;
import com.matheus.HelpDesk.Repository.PessoaRepository;
import com.matheus.HelpDesk.Resources.execption.DataIntegrityViolationException;
import com.matheus.HelpDesk.Resources.execption.ObjNotFoundException;
import com.matheus.HelpDesk.domain.Agente;
import com.matheus.HelpDesk.domain.DTOS.AgenteDTO;
import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteService {

    private final PessoaRepository pessoaRepository;
    private final AgenteRepository agenteRepository;

    public AgenteService(PessoaRepository pessoaRepository, AgenteRepository Repository) {
        this.pessoaRepository = pessoaRepository;
        this.agenteRepository = Repository;
    }


    public Agente findById(Integer id) {
        Optional<Agente> obj = agenteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Agente não encontrado com ID: " + id));
    }

    public List<Agente> findAll() {
        return agenteRepository.findAll();
    }

    public Agente create(AgenteDTO objDTO) {
        objDTO.setId(null);
        validaCPFeEmail(objDTO);
        Agente newObj = new Agente(objDTO);
        return agenteRepository.save(newObj);
    }

    private void validaCPFeEmail(AgenteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }

    }

    public Agente update(Integer id, AgenteDTO objDTO) {
        objDTO.setId(id);
        Agente oldObj = findById(id);
        validaCPFeEmail(objDTO);
        oldObj = new Agente(objDTO);
        return pessoaRepository.save(oldObj);


    }

    public void  delete(Integer id) {
        Agente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Agente possui chamados e não pode ser deletado!");
        }
        agenteRepository.deleteById(id);


    }
}
