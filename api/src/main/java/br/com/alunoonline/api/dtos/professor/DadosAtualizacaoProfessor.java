package br.com.alunoonline.api.dtos.professor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProfessor(
        @NotNull Long id,
        String nome,
        String cpf,
        String email
) {}
