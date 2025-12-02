package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.domain.aluno.*;
import br.com.alunoonline.api.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@SecurityRequirement(name = "bearerAuth") // Swagger
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    // Cadastrar aluno (precisa de autenticação)
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAluno dados,
                                    UriComponentsBuilder uriBuilder,
                                    @AuthenticationPrincipal Usuario usuarioLogado) {

        System.out.println("Usuário logado: " + usuarioLogado.getUsername());

        var aluno = new Aluno(dados);
        repository.save(aluno);

        var uri = uriBuilder.path("/alunos/{id}")
                .buildAndExpand(aluno.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    // Listar todos os alunos (sem parâmetros)
    @GetMapping
    public ResponseEntity<List<DadosListagemAluno>> listar(@AuthenticationPrincipal Usuario usuarioLogado) {

        System.out.println("Usuário logado: " + usuarioLogado.getUsername());

        var alunos = repository.findAll();
        var alunosDTO = alunos.stream()
                .map(DadosListagemAluno::new)
                .toList();

        return ResponseEntity.ok(alunosDTO);
    }

    //Atualizar Aluno
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoAluno dados,
            @AuthenticationPrincipal Usuario usuarioLogado) {

        System.out.println("Usuário logado: " + usuarioLogado.getUsername());

        var aluno = repository.getReferenceById(id);
        aluno.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    // Excluir aluno
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id,
                                  @AuthenticationPrincipal Usuario usuarioLogado) {

        System.out.println("Usuário logado: " + usuarioLogado.getUsername());

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Detalhar aluno por id
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id,
                                   @AuthenticationPrincipal Usuario usuarioLogado) {

        System.out.println("Usuário logado: " + usuarioLogado.getUsername());

        var aluno = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }
}
