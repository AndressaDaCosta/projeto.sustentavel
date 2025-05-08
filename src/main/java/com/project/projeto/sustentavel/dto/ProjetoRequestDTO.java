package com.project.projeto.sustentavel.dto;

import jakarta.validation.constraints.*;

public class ProjetoRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "A região é obrigatória")
    private String regiao;

    @NotNull(message = "A estimativa de redução de CO2 é obrigatória")
    @Positive(message = "A estimativa deve ser um número positivo")
    private Double estimativaReducaoCo2;

    @NotNull(message = "O ID da organização é obrigatório")
    private Long organizacaoId;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Double getEstimativaReducaoCo2() {
        return estimativaReducaoCo2;
    }

    public void setEstimativaReducaoCo2(Double estimativaReducaoCo2) {
        this.estimativaReducaoCo2 = estimativaReducaoCo2;
    }

    public Long getOrganizacaoId() {
        return organizacaoId;
    }

    public void setOrganizacaoId(Long organizacaoId) {
        this.organizacaoId = organizacaoId;
    }
}
