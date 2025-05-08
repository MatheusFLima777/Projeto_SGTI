package com.matheus.HelpDesk.Resources;



import com.matheus.HelpDesk.Services.FuncionarioService;
import com.matheus.HelpDesk.domain.DTOS.FuncionarioDTO;
import com.matheus.HelpDesk.domain.Funcionario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Integer id) {
        Funcionario obj = this.funcionarioService.findById(id);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        List<Funcionario> list = funcionarioService.findAll();
        List<FuncionarioDTO> listDTO = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO objDTO) {
        Funcionario newObj = funcionarioService.create(objDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Integer id, @Valid  @RequestBody FuncionarioDTO objDTO) {
        Funcionario obj = funcionarioService.update(id, objDTO);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }


    @DeleteMapping (value = "/{id}")
    public ResponseEntity<FuncionarioDTO> delete(@PathVariable Integer id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

