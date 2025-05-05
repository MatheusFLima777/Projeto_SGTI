package com.matheus.HelpDesk.Config;

import com.matheus.HelpDesk.Services.DBService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@Profile("dev")
public class DevConfig {

    private final DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    public DevConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @PostConstruct
    public void instanciaDB() {
        if ("create".equalsIgnoreCase(ddlAuto)) {
            dbService.instanciaDB();
        }
    }
}
