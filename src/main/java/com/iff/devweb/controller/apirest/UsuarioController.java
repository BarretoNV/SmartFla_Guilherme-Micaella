package com.iff.devweb.controller.apirest;

import com.iff.devweb.Service.UsuarioService;
import com.iff.devweb.entity.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Buscar usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> buscarPorId(
            @Parameter(description = "ID do usuário a ser buscado") @PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> {
                    // Criando o modelo de usuário com links HATEOAS
                    EntityModel<Usuario> usuarioModel = EntityModel.of(usuario);
                    // Adicionando link para o próprio usuário
                    usuarioModel.add(linkTo(methodOn(UsuarioController.class).buscarPorId(id)).withSelfRel());
                    // Adicionando link para todos os usuários
                    usuarioModel.add(linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withRel("all-usuarios"));
                    return ResponseEntity.ok(usuarioModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todos os usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))
    })
    @GetMapping
    public List<EntityModel<Usuario>> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodosUsuarios();
        return usuarios.stream()
                .map(usuario -> EntityModel.of(usuario,
                        linkTo(methodOn(UsuarioController.class).buscarPorId(usuario.getId())).withSelfRel(),
                        linkTo(methodOn(UsuarioController.class).listarTodosUsuarios()).withRel("all-usuarios")))
                .collect(Collectors.toList());
    }


    @Operation(summary = "Buscar usuário por email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(
            @Parameter(description = "Email do usuário a ser buscado") @PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cadastrar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(
            @Parameter(description = "Dados do usuário a ser cadastrado") @RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    @Operation(summary = "Atualizar usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @Parameter(description = "ID do usuário a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Dados do usuário atualizados") @RequestBody Usuario usuarioAtualizado) {

        return usuarioService.buscarPorId(id)
                .map(usuarioExistente -> {
                    // Atualizando os campos necessários
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    usuarioExistente.setDataNasc(usuarioAtualizado.getDataNasc());
                    usuarioExistente.setTel(usuarioAtualizado.getTel());
                    usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
                    // Outros campos que necessitem ser atualizados

                    Usuario usuarioAtualizadoDb = usuarioService.salvarUsuario(usuarioExistente);
                    return ResponseEntity.ok(usuarioAtualizadoDb);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Deletar usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(
            @Parameter(description = "ID do usuário a ser deletado") @PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
