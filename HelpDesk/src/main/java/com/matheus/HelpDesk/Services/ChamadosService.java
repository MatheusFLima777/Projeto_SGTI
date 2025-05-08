package com.matheus.HelpDesk.Services;

import com.matheus.HelpDesk.Repository.ChamadoRepository;
import com.matheus.HelpDesk.Resources.execption.ObjNotFoundException;
import com.matheus.HelpDesk.domain.Chamado;
import com.matheus.HelpDesk.domain.DTOS.ChamadoDTO;
import com.matheus.HelpDesk.domain.Funcionario;
import com.matheus.HelpDesk.domain.enums.Status;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import com.matheus.HelpDesk.domain.Agente;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadosService {

    private final ChamadoRepository chamadoRepository;
    private final AgenteService agenteService;
    private final FuncionarioService funcionarioService;

    public ChamadosService(ChamadoRepository chamadoRepository,
                           AgenteService agenteService,
                           FuncionarioService funcionarioService) {
        this.chamadoRepository = chamadoRepository;
        this.agenteService = agenteService;
        this.funcionarioService = funcionarioService;
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(@Valid ChamadoDTO objDTO) {
        return chamadoRepository.save(newChamado(objDTO));
    }

    public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);

        // Atualiza apenas os campos necessários
        oldObj.setAgente(agenteService.findById(objDTO.getAgente()));
        oldObj.setFuncionario(funcionarioService.findById(objDTO.getFuncionario()));
        oldObj.setPrioridade(objDTO.getPrioridade());
        oldObj.setTitulo(objDTO.getTitulo());
        oldObj.setObservacoes(objDTO.getObservacoes());

        // Lógica para dataFechamento

        List<Integer> statusParaFechamento = Arrays.asList(6, 7, 8);
        if(objDTO.getStatus() != null && statusParaFechamento.contains(objDTO.getStatus().getCodigo())) {



            // Só seta a data de fechamento se ainda não estiver setada
            if(oldObj.getDataFechamento() == null) {
                oldObj.setDataFechamento(LocalDateTime.now());

            }
        } else {
            // Se status não for 6, limpa a data de fechamento
            oldObj.setDataFechamento(null);
        }

        oldObj.setStatus(objDTO.getStatus());

        return chamadoRepository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Agente agente = agenteService.findById(obj.getAgente());
        Funcionario funcionario = funcionarioService.findById(obj.getFuncionario());

        Chamado chamado = new Chamado();
        if(obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        chamado.setAgente(agente);
        chamado.setFuncionario(funcionario);
        chamado.setPrioridade(obj.getPrioridade());
        chamado.setStatus(obj.getStatus());
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());

        // Lógica para dataFechamento no create
        if(obj.getStatus() != null && obj.getStatus() == Status.FECHADO) {
            chamado.setDataFechamento(LocalDateTime.now());
        }
        return chamado;
    }
}