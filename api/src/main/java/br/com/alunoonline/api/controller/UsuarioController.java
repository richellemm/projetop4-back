package br.com.alunoonline.api.controller;

import jakarta.validation.Valid;
import br.com.alunoonline.api.dtos.DadosCadastroUsuario;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cadastros")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastro (@RequestBody @Valid
                                    DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){

        if (repository.findByLogin(dados.login()) !=null){
            return ResponseEntity.badRequest().body("Login já cadastrado!");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados.login(), senhaCriptografada);

        repository.save(usuario);
        return null;

    }

}