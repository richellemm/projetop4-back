package br.com.alunoonline.api.domain.aluno;

public record AlunoResponse(
        Long id,
        String nome,
        String cpf
) {}
