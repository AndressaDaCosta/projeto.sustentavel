package com.project.projeto.sustentavel.repository;

import com.project.projeto.sustentavel.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p " +
           "WHERE (:regiao IS NULL OR LOWER(p.regiao) LIKE LOWER(CONCAT('%', :regiao, '%'))) " +
           "AND (:nomeOrganizacao IS NULL OR LOWER(p.organizacao.nome) LIKE LOWER(CONCAT('%', :nomeOrganizacao, '%')))")
    List<Projeto> findByRegiaoAndNomeOrganizacao(String regiao, String nomeOrganizacao);
}
