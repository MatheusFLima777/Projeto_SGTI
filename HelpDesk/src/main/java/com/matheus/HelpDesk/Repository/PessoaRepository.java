package com.matheus.HelpDesk.Repository;

import com.matheus.HelpDesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {


}
