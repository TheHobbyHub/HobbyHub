package br.fatec.hobby_hub.controller;

import br.fatec.hobby_hub.dto.LoginDTO;
import br.fatec.hobby_hub.dto.UsuarioAtualizacaoDTO;
import br.fatec.hobby_hub.dto.UsuarioCadastroDTO;
import br.fatec.hobby_hub.dto.UsuarioRespostaDTO;
import br.fatec.hobby_hub.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioRespostaDTO> cadastrar(@RequestBody UsuarioCadastroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespostaDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRespostaDTO> alterarDados(@PathVariable Long id, @RequestBody UsuarioAtualizacaoDTO dto) {
        return ResponseEntity.ok(service.alterarDados(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
        boolean sucesso = service.autenticar(dto.email(), dto.senha());
        if (sucesso) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
}