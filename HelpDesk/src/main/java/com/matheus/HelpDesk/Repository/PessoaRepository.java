package com.matheus.HelpDesk.Repository;

import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {


    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);



}
