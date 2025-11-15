package br.com.alunoonline.api.dtos.professor;

import br.com.alunoonline.api.model.Professor;

public record DadosListagemProfessor(Long id, String nome, String cpf, String email) {

    public DadosListagemProfessor(Professor professor) {
        this(
                professor.getId(),
                professor.getNome(),
                professor.getCpf(),
                professor.getEmail()
        );
    }
}
