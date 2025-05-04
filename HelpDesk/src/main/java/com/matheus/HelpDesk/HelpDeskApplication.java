package com.matheus.HelpDesk;

import com.matheus.HelpDesk.Repository.AdminRepository;
import com.matheus.HelpDesk.Repository.AgenteRepository;
import com.matheus.HelpDesk.Repository.ChamadoRepository;
import com.matheus.HelpDesk.Repository.FuncionarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpDeskApplication {

    private final FuncionarioRepository funcionarioRepository;
    private final AdminRepository adminRepository;
    private final ChamadoRepository chamadoRepository;
    private final AgenteRepository agenteRepository;

    public HelpDeskApplication(FuncionarioRepository funcionarioRepository,
                               AdminRepository adminRepository,
                               ChamadoRepository chamadoRepository,
                               AgenteRepository agenteRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.adminRepository = adminRepository;
        this.chamadoRepository = chamadoRepository;
        this.agenteRepository = agenteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HelpDeskApplication.class, args);
    }


}

