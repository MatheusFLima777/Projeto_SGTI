package com.matheus.HelpDesk.Services;

import com.matheus.HelpDesk.Repository.FuncionarioRepository;
import com.matheus.HelpDesk.Repository.PessoaRepository;
import com.matheus.HelpDesk.Resources.execption.DataIntegrityViolationException;
import com.matheus.HelpDesk.Resources.execption.ObjNotFoundException;
import com.matheus.HelpDesk.domain.DTOS.FuncionarioDTO;
import com.matheus.HelpDesk.domain.Funcionario;
import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final PessoaRepository pessoaRepository;
    private final FuncionarioRepository funcionarioeRepository;

    public FuncionarioService(PessoaRepository pessoaRepository, FuncionarioRepository Repository) {
        this.pessoaRepository = pessoaRepository;
        this.funcionarioeRepository = Repository;
    }


    public Funcionario findById(Integer id) {
        Optional<Funcionario> obj = funcionarioeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Funcionario não encontrado com ID: " + id));
    }

    public List<Funcionario> findAll() {
        return funcionarioeRepository.findAll();
    }

    public Funcionario create(FuncionarioDTO objDTO) {
        objDTO.setId(null);
        validaCPFeEmail(objDTO);
        Funcionario newObj = new Funcionario(objDTO);
        return funcionarioeRepository.save(newObj);
    }

    private void validaCPFeEmail(FuncionarioDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }

    }

    public Funcionario update(Integer id, FuncionarioDTO objDTO) {
        objDTO.setId(id);
        Funcionario oldObj = findById(id);
        validaCPFeEmail(objDTO);
        oldObj = new Funcionario(objDTO);
        return pessoaRepository.save(oldObj);


    }

    public void  delete(Integer id) {
        Funcionario obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Funcionario possui chamados e não pode ser deletado!");
        }
        funcionarioeRepository.deleteById(id);


    }
}
