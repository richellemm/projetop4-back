package br.com.alunoonline.api.domain.aluno;

import jakarta.validation.constraints.NotBlank;

public record AlunoRequest(
        @NotBlank String nome,
        @NotBlank String cpf
) {}
