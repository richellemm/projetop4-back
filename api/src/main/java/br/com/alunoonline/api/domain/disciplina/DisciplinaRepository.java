package br.com.alunoonline.api.domain.disciplina;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository <Disciplina, Long>{
    List<Disciplina> findByProfessorId(Long professorId);
}