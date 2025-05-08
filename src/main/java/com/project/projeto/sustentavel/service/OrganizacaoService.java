package com.project.projeto.sustentavel.service;

import com.project.projeto.sustentavel.model.Organizacao;
import com.project.projeto.sustentavel.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizacaoService {

    @Autowired
    private OrganizacaoRepository repository;

    public Organizacao save(Organizacao organizacao) {
        return repository.save(organizacao);
    }

    public List<Organizacao> findAll() {
        return repository.findAll();
    }

    public Optional<Organizacao> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Organizacao> findByNomeAndContato(String nome, String contato) {
        return repository.findByNomeAndContato(nome, contato);
    }
}
