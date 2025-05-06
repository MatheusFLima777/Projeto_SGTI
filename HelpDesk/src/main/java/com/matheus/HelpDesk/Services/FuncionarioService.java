package com.matheus.HelpDesk.Services;


import com.matheus.HelpDesk.Repository.FuncionarioRepository;
import com.matheus.HelpDesk.domain.DTOS.FuncionarioDTO;
import com.matheus.HelpDesk.domain.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;


    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
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
}
