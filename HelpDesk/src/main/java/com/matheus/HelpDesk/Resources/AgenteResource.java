package com.matheus.HelpDesk.Resources;

import com.matheus.HelpDesk.Services.AgenteService;
import com.matheus.HelpDesk.domain.Agente;
import com.matheus.HelpDesk.domain.DTOS.AgenteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/agentes")
public class AgenteResource {

    private AgenteService agenteService;

    public AgenteResource(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    // localhost:8080/agente/1
    @GetMapping(value = "/{id}")
    public ResponseEntity<AgenteDTO> findById(@PathVariable Integer id) {
        Agente obj = this.agenteService.findById(id);
        return ResponseEntity.ok().body(new AgenteDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<AgenteDTO>> findAll() {
        List<Agente> list = agenteService.findAll();
        List<AgenteDTO> listDTO = list.stream().map(obj -> new AgenteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<AgenteDTO> create(@RequestBody AgenteDTO objDTO) {
        Agente newObj = agenteService.create(objDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                  .path("/{id}").buildAndExpand(newObj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
