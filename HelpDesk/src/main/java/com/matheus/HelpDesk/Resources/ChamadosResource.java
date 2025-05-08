package com.matheus.HelpDesk.Resources;


import com.matheus.HelpDesk.Services.ChamadosService;
import com.matheus.HelpDesk.domain.Chamado;
import com.matheus.HelpDesk.domain.DTOS.ChamadoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadosResource {

    private ChamadosService chamadosService;
    public ChamadosResource(ChamadosService chamadosService) {
        this.chamadosService = chamadosService;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id ){
        Chamado obj = chamadosService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = chamadosService.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO){
        Chamado obj = chamadosService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

}
@PutMapping(value = "/{id}")
public ResponseEntity<ChamadoDTO> update(@PathVariable @Valid Integer id, @RequestBody ChamadoDTO objDTO){
        Chamado newObj = chamadosService.update(id, objDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(newObj));
    }


}
