package br.fatec.hobby_hub.dto;

import br.fatec.hobby_hub.infrastructure.entity.StatusUsuario;
import br.fatec.hobby_hub.infrastructure.entity.Usuario;

public record UsuarioRespostaDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        StatusUsuario status
) {
    public UsuarioRespostaDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getStatus()
        );
    }
}
