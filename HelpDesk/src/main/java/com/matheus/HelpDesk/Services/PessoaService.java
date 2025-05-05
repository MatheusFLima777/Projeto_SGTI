package com.matheus.HelpDesk.Services;


import com.matheus.HelpDesk.Repository.PessoaRepository;
import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;


    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa findById(Integer id) {
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: "+ id));
    }
}
