package br.com.alunoonline.api.dtos.professor;

import br.com.alunoonline.api.model.Professor;

public record DadosDetalhamentoProfessor(Long id, String nome, String cpf, String email) {

    public DadosDetalhamentoProfessor(Professor professor) {
        this(
                professor.getId(),
                professor.getNome(),
                professor.getCpf(),
                professor.getEmail()
        );
    }
}
