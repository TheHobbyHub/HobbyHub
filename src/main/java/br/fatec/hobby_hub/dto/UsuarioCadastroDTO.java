package br.fatec.hobby_hub.dto;

public record UsuarioCadastroDTO(
        String nome,
        String sobrenome,
        String email,
        String senha,
        String cpf,
        String telefone
) {}
