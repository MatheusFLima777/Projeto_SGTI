package com.matheus.HelpDesk.Services;

import com.matheus.HelpDesk.Repository.AdminRepository;
import com.matheus.HelpDesk.Repository.AgenteRepository;
import com.matheus.HelpDesk.Repository.ChamadoRepository;
import com.matheus.HelpDesk.Repository.FuncionarioRepository;
import com.matheus.HelpDesk.domain.Admin;
import com.matheus.HelpDesk.domain.Agente;
import com.matheus.HelpDesk.domain.Chamado;
import com.matheus.HelpDesk.domain.Funcionario;
import com.matheus.HelpDesk.domain.enums.Perfil;
import com.matheus.HelpDesk.domain.enums.Prioridade;
import com.matheus.HelpDesk.domain.enums.Status;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    private final FuncionarioRepository funcionarioRepository;
    private final AdminRepository adminRepository;
    private final ChamadoRepository chamadoRepository;
    private final AgenteRepository agenteRepository;


    public DBService(FuncionarioRepository funcionarioRepository,
                     AdminRepository adminRepository,
                     ChamadoRepository chamadoRepository,
                     AgenteRepository agenteRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.adminRepository = adminRepository;
        this.chamadoRepository = chamadoRepository;
        this.agenteRepository = agenteRepository;
    }

    public void instanciaDB() {

        Agente agente1 = new Agente(null,
                "Matheus Ferreira Lima",
                "4576966970",
                "matheus.lima@dellavolpe.com.br",
                "25022003ma"
        );
        agente1.addPerfil(Perfil.AGENTE);

        Agente agente2 = new Agente(null,
                "Rafael Dutra",
                "54238909852",
                "rafael.dutra@dellavolpe.com.br",
                "321123");
        agente2.addPerfil(Perfil.AGENTE);

        Funcionario funcionario1 = new Funcionario(null,
                "Tyrion Bjorn",
                "23302394403",
                "Tyrion.silva@dellavolpe.com.br",
                "viking123"
        );

        Admin admin1 = new Admin(null,
                "Alison Costa",
                "98669013860",
                "alison.costa@dellavolpe.com.br",
                "Teste123",
                null);
        admin1.addPerfil(Perfil.ADMIN);

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.Novo,
                "TESTE 01", "PRIMEIRO CHAMADO", funcionario1, agente1);

        agenteRepository.saveAll(Arrays.asList(agente1, agente2));
        funcionarioRepository.save(funcionario1);
        chamadoRepository.save(chamado1);
        adminRepository.save(admin1);
    }
}

