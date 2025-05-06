package com.matheus.HelpDesk.Resources;



import com.matheus.HelpDesk.Services.FuncionarioService;
import com.matheus.HelpDesk.domain.DTOS.FuncionarioDTO;
import com.matheus.HelpDesk.domain.Funcionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {

    private final FuncionarioService service;
    private final FuncionarioService funcionarioService;

    public FuncionarioResource(FuncionarioService service, FuncionarioService funcionarioService) {
        this.service = service;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Integer id) {
        Funcionario funcionario = service.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @GetMapping
    public  ResponseEntity<List<FuncionarioDTO>> findAll(){
        List<Funcionario> list = funcionarioService.findAll();
        List<FuncionarioDTO> listDTO = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }
}
