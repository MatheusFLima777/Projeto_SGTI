package com.matheus.HelpDesk.Repository;

import com.matheus.HelpDesk.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {


}