package com.funcionario.repositories;

import com.funcionario.entities.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {

    //Exemplo usando Panache Query com LIKE e paginação
    public List<Pessoa> buscarPorNome(String nome, int page, int size) {
        return find("nome LIKE ?1", "%" + nome + "%")
                .page(page, size)
                .list();
    }

    //Exemplo usando JPQL
    public List<Pessoa> buscarPorNomeJPQL (String nome){
        return list("SELECT p FROM Pessoa p WHERE p.nome = ?1", nome);
    }

    //Exemplo usando Native Query
    public List<Pessoa> buscarPorNomeNativeQuery (String nome){
        return list("SELECT * FROM pessoas WHERE nome = ?1", nome);
    }

    //Exemplo usando Named Query
    public List<Pessoa> buscarPorNomeNamedQuery (String nome){
        return list("Pessoa.buscarPorNome", nome);
    }

    //Exemplo usando Native Query 2
    @SuppressWarnings("unchecked")
    public List<Pessoa> buscarPorNomeNative(String nome) {

        String sql = """
                SELECT *
                FROM pessoas
                WHERE UPPER(nome) LIKE UPPER(CONCAT('%', ?1, '%'))
                """;

        return (List<Pessoa>) getEntityManager()
                .createNativeQuery(sql, Pessoa.class)
                .setParameter(1, nome)
                .getResultList();
    }

}