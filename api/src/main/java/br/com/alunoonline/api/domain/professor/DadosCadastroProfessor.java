package br.com.alunoonline.api.domain.professor;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroProfessor(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank String email
) {}
