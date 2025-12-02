package br.com.alunoonline.api.service;

import br.com.alunoonline.api.domain.disciplina.Disciplina;
import br.com.alunoonline.api.domain.disciplina.DisciplinaRepository;
import br.com.alunoonline.api.domain.disciplina.MatriculaAlunoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private MatriculaAlunoRepository matriculaAlunoRepository;

    public void criarDisciplina(Disciplina disciplina){
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarTodasDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarDisciplinaPorId(Long id){
        return disciplinaRepository.findById(id);
    }

    public void deletarDisciplinaPorId(Long id){
        // Verifica se existem alunos matriculados
        boolean possuiAlunosMatriculados = !matriculaAlunoRepository.findByDisciplinaId(id).isEmpty();

        if (possuiAlunosMatriculados) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possível deletar a disciplina pois existem alunos matriculados."
            );
        }

        // Se não houver alunos, deleta a disciplina
        disciplinaRepository.deleteById(id);
    }

    public void atualizarDisciplinaPorId(Long id, Disciplina disciplina){
        Optional<Disciplina> disciplinaDoBancoDeDados = buscarDisciplinaPorId(id);

        if (disciplinaDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada no banco de dados.");
        }

        Disciplina disciplinaParaEditar = disciplinaDoBancoDeDados.get();
        disciplinaParaEditar.setNome(disciplina.getNome());
        disciplinaParaEditar.setCargaHoraria(disciplina.getCargaHoraria());
        disciplinaParaEditar.setProfessor(disciplina.getProfessor());
        disciplinaRepository.save(disciplinaParaEditar);
    }

    public List<Disciplina> listarDisciplinasDoProf(Long professorId){
        return disciplinaRepository.findByProfessorId(professorId);
    }
}
