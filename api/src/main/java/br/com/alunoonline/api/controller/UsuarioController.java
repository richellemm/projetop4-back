package br.com.alunoonline.api.controller;

import jakarta.validation.Valid;
import br.com.alunoonline.api.domain.usuario.DadosCadastroUsuario;
import br.com.alunoonline.api.domain.usuario.Usuario;
import br.com.alunoonline.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cadastros")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados,
                                    UriComponentsBuilder uriBuilder) {

        Usuario usuario = usuarioService.cadastrar(dados);

        var uri = uriBuilder.path("/cadastros/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri).body(usuario);
    }
}
