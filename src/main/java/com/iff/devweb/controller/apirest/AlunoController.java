package com.iff.devweb.controller.apirest;

import com.iff.devweb.Service.AlunoService;
import com.iff.devweb.entity.Aluno;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    // Injeção do serviço AlunoService
    @Autowired
    private AlunoService alunoService;

    @Operation(summary = "Listar todos os alunos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todos os alunos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class)))
    })
    @GetMapping
    public List<EntityModel<Aluno>> listarTodosAlunos() {
        List<Aluno> alunos = alunoService.listarTodosAlunos();
        return alunos.stream()
                .map(aluno -> EntityModel.of(aluno,
                        linkTo(methodOn(AlunoController.class).buscarPorId(aluno.getId())).withSelfRel(),
                        linkTo(methodOn(AlunoController.class).listarTodosAlunos()).withRel("all-alunos")))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Buscar aluno pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Aluno>> buscarPorId(
            @Parameter(description = "ID do aluno a ser buscado") @PathVariable Long id) {
        return alunoService.buscarPorId(id)
                .map(aluno -> {
                    EntityModel<Aluno> alunoModel = EntityModel.of(aluno,
                            linkTo(methodOn(AlunoController.class).buscarPorId(id)).withSelfRel(),
                            linkTo(methodOn(AlunoController.class).listarTodosAlunos()).withRel("all-alunos"));
                    return ResponseEntity.ok(alunoModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar alunos pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alunos com o nome fornecido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum aluno encontrado com o nome fornecido",
                    content = @Content)
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<EntityModel<Aluno>>> buscarPorNome(
            @Parameter(description = "Nome do aluno a ser buscado") @PathVariable String nome) {
        List<Aluno> alunos = alunoService.buscarPorNome(nome);
        if (alunos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<EntityModel<Aluno>> alunoModels = alunos.stream()
                .map(aluno -> EntityModel.of(aluno,
                        linkTo(methodOn(AlunoController.class).buscarPorId(aluno.getId())).withSelfRel(),
                        linkTo(methodOn(AlunoController.class).listarTodosAlunos()).withRel("all-alunos")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(alunoModels);
    }

    @Operation(summary = "Cadastrar um novo aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<EntityModel<Aluno>> salvarAluno(
            @Parameter(description = "Dados do aluno a ser cadastrado") @RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.salvarAluno(aluno);
        EntityModel<Aluno> alunoModel = EntityModel.of(novoAluno,
                linkTo(methodOn(AlunoController.class).buscarPorId(novoAluno.getId())).withSelfRel(),
                linkTo(methodOn(AlunoController.class).listarTodosAlunos()).withRel("all-alunos"));
        return ResponseEntity.status(201).body(alunoModel);
    }

    @Operation(summary = "Atualizar aluno pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Aluno>> atualizarAluno(
            @Parameter(description = "ID do aluno a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Dados do aluno atualizados") @RequestBody Aluno alunoAtualizado) {

        return alunoService.buscarPorId(id)
                .map(alunoExistente -> {
                    alunoExistente.setNome(alunoAtualizado.getNome());
                    alunoExistente.setEmail(alunoAtualizado.getEmail());
                    alunoExistente.setDataNasc(alunoAtualizado.getDataNasc());
                    alunoExistente.setTel(alunoAtualizado.getTel());
                    alunoExistente.setEndereco(alunoAtualizado.getEndereco());

                    Aluno alunoAtualizadoDb = alunoService.salvarAluno(alunoExistente);

                    EntityModel<Aluno> alunoModel = EntityModel.of(alunoAtualizadoDb,
                            linkTo(methodOn(AlunoController.class).buscarPorId(id)).withSelfRel(),
                            linkTo(methodOn(AlunoController.class).listarTodosAlunos()).withRel("all-alunos"));

                    return ResponseEntity.ok(alunoModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar aluno pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(
            @Parameter(description = "ID do aluno a ser deletado") @PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
