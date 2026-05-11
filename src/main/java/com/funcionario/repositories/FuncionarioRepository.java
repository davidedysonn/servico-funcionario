package com.funcionario.repositories;

import com.funcionario.entities.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
}
