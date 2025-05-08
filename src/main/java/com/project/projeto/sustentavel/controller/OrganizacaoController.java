package com.project.projeto.sustentavel.controller;

import com.project.projeto.sustentavel.model.Organizacao;
import com.project.projeto.sustentavel.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService service;

    @PostMapping
    public ResponseEntity<Organizacao> create(@RequestBody Organizacao organizacao) {
        return ResponseEntity.ok(service.save(organizacao));
    }

    @GetMapping
    public List<Organizacao> getAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String contato) {
        if (nome != null || contato != null) {
            return service.findByNomeAndContato(nome, contato);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizacao> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizacao> update(@PathVariable Long id, @RequestBody Organizacao nova) {
        return service.findById(id).map(antiga -> {
            antiga.setNome(nova.getNome());
            antiga.setContato(nova.getContato());
            return ResponseEntity.ok(service.save(antiga));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}