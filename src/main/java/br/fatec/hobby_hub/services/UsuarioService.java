package br.fatec.hobby_hub.services;

import br.fatec.hobby_hub.dto.UsuarioAtualizacaoDTO;
import br.fatec.hobby_hub.dto.UsuarioCadastroDTO;
import br.fatec.hobby_hub.dto.UsuarioRespostaDTO;
import br.fatec.hobby_hub.infrastructure.entity.StatusUsuario;
import br.fatec.hobby_hub.infrastructure.entity.Usuario;
import br.fatec.hobby_hub.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioRespostaDTO cadastrar(UsuarioCadastroDTO dto){
        if (repository.existsByEmail(dto.email())){
            throw new RuntimeException("Email já cadastrado");
        }
        if (repository.existsByCpf(dto.cpf())){
            throw new RuntimeException("CPF já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setSenha(usuario.getSenha());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setStatus(StatusUsuario.ATIVO);

        Usuario salvo = repository.save(usuario);
        return new UsuarioRespostaDTO(salvo);
    }

    public List<UsuarioRespostaDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(UsuarioRespostaDTO::new)
                .toList();
    }

    public UsuarioRespostaDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioRespostaDTO(usuario);
    }

    public UsuarioRespostaDTO alterarDados(Long id, UsuarioAtualizacaoDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.nome());
        usuario.setTelefone(dto.telefone());
        return new UsuarioRespostaDTO(repository.save(usuario));
    }

    public void excluir(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repository.delete(usuario);
    }

    public boolean autenticar(String email, String senha) {
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        return passwordEncoder.matches(senha, usuario.getSenha());
    }

}
