package com.matheus.HelpDesk.Config;

import com.matheus.HelpDesk.Services.DBService;
import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;


    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @PostConstruct
    public void instanciaDB() {
        dbService.instanciaDB();
    }
}
