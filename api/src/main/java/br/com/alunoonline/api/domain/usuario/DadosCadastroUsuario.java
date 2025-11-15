package br.com.alunoonline.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank(message = "Login é obrigatório!") String login,
        @NotBlank(message = "Senha é obrigatório!") String senha
) {
}