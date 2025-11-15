package br.com.alunoonline.api.domain.aluno;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public DadosDetalhamentoAluno(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf()
        );
    }
}
