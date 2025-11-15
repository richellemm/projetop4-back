package br.com.alunoonline.api.service;

import br.com.alunoonline.api.domain.aluno.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Transactional
    public DadosDetalhamentoAluno cadastrar(DadosCadastroAluno dados) {
        Aluno aluno = new Aluno(dados);
        repository.save(aluno);
        return new DadosDetalhamentoAluno(aluno);
    }

    @Transactional
    public DadosDetalhamentoAluno atualizar(Long id, DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.getReferenceById(id);
        aluno.atualizarInformacoes(dados);
        return new DadosDetalhamentoAluno(aluno);
    }

    @Transactional(readOnly = true)
    public DadosDetalhamentoAluno buscarPorId(Long id) {
        var aluno = repository.getReferenceById(id);
        return new DadosDetalhamentoAluno(aluno);
    }
}
