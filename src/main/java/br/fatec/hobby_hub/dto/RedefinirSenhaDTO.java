package br.fatec.hobby_hub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RedefinirSenhaDTO(
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @NotBlank(message = "O código é obrigatório")
        String codigo,

        @NotBlank(message = "A nova senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String novaSenha
) {}