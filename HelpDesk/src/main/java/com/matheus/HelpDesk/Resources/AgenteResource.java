package com.matheus.HelpDesk.Resources;

import com.matheus.HelpDesk.Services.AgenteService;
import com.matheus.HelpDesk.domain.Agente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/agente")
public class AgenteResource {

    private AgenteService agenteService;
    public AgenteResource(AgenteService agenteService) {
        this.agenteService = agenteService;
    }
    // localhost:8080/agente/1
    @GetMapping(value = "/{id}")
    public ResponseEntity<Agente> findById(@PathVariable Integer id){
        Agente obj = this.agenteService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
