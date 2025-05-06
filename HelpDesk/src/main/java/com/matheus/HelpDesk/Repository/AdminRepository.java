package com.matheus.HelpDesk.Repository;

import com.matheus.HelpDesk.domain.Admin;

import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);

}


