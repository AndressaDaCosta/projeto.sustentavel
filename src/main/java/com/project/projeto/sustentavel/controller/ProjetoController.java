package com.project.projeto.sustentavel.controller;

import com.project.projeto.sustentavel.dto.ProjetoRequestDTO;
import com.project.projeto.sustentavel.model.Projeto;
import com.project.projeto.sustentavel.service.ProjetoService;
import com.project.projeto.sustentavel.service.OrganizacaoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private OrganizacaoService organizacaoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProjetoRequestDTO dto) {
        var organizacaoOptional = organizacaoService.findById(dto.getOrganizacaoId());

        if (organizacaoOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Organização não encontrada");
        }

        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setRegiao(dto.getRegiao());
        projeto.setEstimativaReducaoCo2(dto.getEstimativaReducaoCo2());
        projeto.setOrganizacao(organizacaoOptional.get());

        return ResponseEntity.ok(projetoService.save(projeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ProjetoRequestDTO dto) {
        var projetoOptional = projetoService.findById(id);
        if (projetoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var projeto = projetoOptional.get();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setRegiao(dto.getRegiao());
        projeto.setEstimativaReducaoCo2(dto.getEstimativaReducaoCo2());

        var organizacaoOptional = organizacaoService.findById(dto.getOrganizacaoId());
        if (organizacaoOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Organização não encontrada");
        }

        projeto.setOrganizacao(organizacaoOptional.get());
        return ResponseEntity.ok(projetoService.save(projeto));
    }

    @GetMapping
    public List<Projeto> getAll(
            @RequestParam(required = false) String regiao,
            @RequestParam(required = false) String nomeOrganizacao) {
        if (regiao != null || nomeOrganizacao != null) {
            return projetoService.findByRegiaoAndNomeOrganizacao(regiao, nomeOrganizacao);
        }
        return projetoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getById(@PathVariable Long id) {
        return projetoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (projetoService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        projetoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
