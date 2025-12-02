package br.com.alunoonline.api.domain.disciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAluno, Long> {

    // Buscar todas as matrículas de um aluno
    List<MatriculaAluno> findByAlunoId(Long alunoId);

    // Buscar todas as matrículas de uma disciplina
    List<MatriculaAluno> findByDisciplinaId(Long disciplinaId);
}

