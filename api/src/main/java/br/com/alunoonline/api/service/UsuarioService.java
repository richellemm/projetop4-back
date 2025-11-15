package br.com.alunoonline.api.service;

import br.com.alunoonline.api.domain.usuario.DadosCadastroUsuario;
import br.com.alunoonline.api.domain.usuario.Usuario;
import br.com.alunoonline.api.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario cadastrar(DadosCadastroUsuario dados) {

        if (repository.findByLogin(dados.login()) != null) {
            throw new RuntimeException("Login já cadastrado!");
        }

        var senhaCriptografada = passwordEncoder.encode(dados.senha());
        var usuario = new Usuario(dados.login(), senhaCriptografada);

        repository.save(usuario);

        return usuario;
    }
}
