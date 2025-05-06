package com.matheus.HelpDesk.Services;


import com.matheus.HelpDesk.Repository.FuncionarioRepository;
import com.matheus.HelpDesk.Repository.PessoaRepository;
import com.matheus.HelpDesk.Resources.execption.DataIntegrityViolationException;
import com.matheus.HelpDesk.domain.DTOS.FuncionarioDTO;
import com.matheus.HelpDesk.domain.Funcionario;
import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final PessoaRepository pessoaRepository;


    public FuncionarioService(FuncionarioRepository funcionarioRepository, PessoaRepository pessoaRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Funcionario findById(Integer id) {
        Optional<Funcionario> obj = funcionarioRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }
    public Funcionario create(FuncionarioDTO objDTO){
        objDTO.setId(null);
        Funcionario newObj = new Funcionario(objDTO);
        return funcionarioRepository.save(newObj);
    }
    private void validaCPFeEmail(FuncionarioDTO objDTO) {
        Optional<Pessoa> obj = funcionarioRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }

        obj = funcionarioRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
