package com.iff.devweb.controller.apirest;

import com.iff.devweb.entity.Treino;
import com.iff.devweb.Service.TreinoService;
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
@RequestMapping("/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @Operation(summary = "Listar todos os treinos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todos os treinos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treino.class)))
    })
    @GetMapping
    public List<EntityModel<Treino>> listarTodosTreinos() {
        List<Treino> treinos = treinoService.listarTodosTreinos();
        return treinos.stream()
                .map(treino -> EntityModel.of(treino,
                        linkTo(methodOn(TreinoController.class).buscarPorId(treino.getId())).withSelfRel(),
                        linkTo(methodOn(TreinoController.class).listarTodosTreinos()).withRel("all-treinos")))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Buscar treino pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treino encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treino.class))),
            @ApiResponse(responseCode = "404", description = "Treino não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Treino>> buscarPorId(
            @Parameter(description = "ID do treino a ser buscado") @PathVariable Long id) {
        return treinoService.buscarPorId(id)
                .map(treino -> {
                    EntityModel<Treino> treinoModel = EntityModel.of(treino,
                            linkTo(methodOn(TreinoController.class).buscarPorId(id)).withSelfRel(),
                            linkTo(methodOn(TreinoController.class).listarTodosTreinos()).withRel("all-treinos"));
                    return ResponseEntity.ok(treinoModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cadastrar um novo treino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Treino cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treino.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<EntityModel<Treino>> salvarTreino(
            @Parameter(description = "Dados do treino a ser cadastrado") @RequestBody Treino treino) {
        Treino novoTreino = treinoService.salvarTreino(treino);
        EntityModel<Treino> treinoModel = EntityModel.of(novoTreino,
                linkTo(methodOn(TreinoController.class).buscarPorId(novoTreino.getId())).withSelfRel(),
                linkTo(methodOn(TreinoController.class).listarTodosTreinos()).withRel("all-treinos"));
        return ResponseEntity.status(201).body(treinoModel);
    }

    @Operation(summary = "Atualizar treino pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treino atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treino.class))),
            @ApiResponse(responseCode = "404", description = "Treino não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Treino>> atualizarTreino(
            @Parameter(description = "ID do treino a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Dados do treino atualizados") @RequestBody Treino treinoAtualizado) {
        return treinoService.buscarPorId(id)
                .map(treinoExistente -> {
                    treinoExistente.setNomeTreino(treinoAtualizado.getNomeTreino());
                    treinoExistente.setDescricao(treinoAtualizado.getDescricao());
                    treinoExistente.setDuracao(treinoAtualizado.getDuracao());
                    treinoExistente.setData(treinoAtualizado.getData());
                    treinoExistente.setHorario(treinoAtualizado.getHorario());
                    treinoExistente.setTipoTreino(treinoAtualizado.getTipoTreino());
                    treinoExistente.setNivelDificuldade(treinoAtualizado.getNivelDificuldade());
                    treinoExistente.setObservacoes(treinoAtualizado.getObservacoes());
                    treinoExistente.setFeedbackTreinador(treinoAtualizado.getFeedbackTreinador());
                    treinoExistente.setFeedbackAluno(treinoAtualizado.getFeedbackAluno());

                    Treino treinoAtualizadoDb = treinoService.salvarTreino(treinoExistente);

                    EntityModel<Treino> treinoModel = EntityModel.of(treinoAtualizadoDb,
                            linkTo(methodOn(TreinoController.class).buscarPorId(id)).withSelfRel(),
                            linkTo(methodOn(TreinoController.class).listarTodosTreinos()).withRel("all-treinos"));
                    return ResponseEntity.ok(treinoModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar treino pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Treino deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Treino não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreino(
            @Parameter(description = "ID do treino a ser deletado") @PathVariable Long id) {
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }
}
