package br.fatec.hobby_hub.dto;

public record UsuarioCadastroDTO(
        String nome,
        String email,
        String senha,
        String cpf,
        String telefone
) {}
