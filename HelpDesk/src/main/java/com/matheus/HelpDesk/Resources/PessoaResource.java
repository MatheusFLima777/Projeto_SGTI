package com.matheus.HelpDesk.Resources;



import com.matheus.HelpDesk.Services.PessoaService;
import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private final PessoaService service;

    public PessoaResource(PessoaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Integer id) {
        Pessoa pessoa = service.findById(id);
        return ResponseEntity.ok().body(pessoa);
    }
}
