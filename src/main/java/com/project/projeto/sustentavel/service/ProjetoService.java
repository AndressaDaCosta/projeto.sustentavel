package com.project.projeto.sustentavel.service;

import com.project.projeto.sustentavel.model.Projeto;
import com.project.projeto.sustentavel.repository.ProjetoRepository;
import com.project.projeto.sustentavel.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    public Projeto save(Projeto projeto) {
        if (!organizacaoRepository.existsById(projeto.getOrganizacao().getId())) {
            throw new RuntimeException("Organização não encontrada");
        }
        return projetoRepository.save(projeto);
    }

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }

    public void deleteById(Long id) {
        projetoRepository.deleteById(id);
    }

    public List<Projeto> findByRegiaoAndNomeOrganizacao(String regiao, String nomeOrganizacao) {
        return projetoRepository.findByRegiaoAndNomeOrganizacao(regiao, nomeOrganizacao);
    }

}