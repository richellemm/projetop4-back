package br.com.alunoonline.api.domain.aluno;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(

        @NotNull
        Long id,

        String nome,
        String email,
        String curso,
        String cpf

) {}
