package com.iff.devweb.controller.apirest;

import com.iff.devweb.Service.TreinadorService;
import com.iff.devweb.entity.Treinador;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
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

@RestController
@RequestMapping("/treinadores")
public class TreinadorController {

    @Autowired
    private TreinadorService treinadorService;

    @Operation(summary = "Listar todos os treinadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todos os treinadores retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treinador.class)))
    })
    @GetMapping
    public List<Treinador> listarTodosTreinadores() {
        return treinadorService.listarTodosTreinadores();
    }

    @Operation(summary = "Buscar treinador pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Treinador>> buscarPorId(
            @Parameter(description = "ID do treinador a ser buscado") @PathVariable Long id) {

        return treinadorService.buscarPorId(id)
                .map(treinador -> {
                    // Cria um modelo de entidade e adiciona os links
                    EntityModel<Treinador> treinadorModel = EntityModel.of(treinador);

                    // Adiciona o link para o próprio recurso (self)
                    treinadorModel.add(linkTo(methodOn(TreinadorController.class).buscarPorId(id)).withSelfRel());

                    // Adiciona link para todos os treinadores
                    treinadorModel.add(linkTo(methodOn(TreinadorController.class).listarTodosTreinadores()).withRel("all-treinadores"));

                    // Adiciona link para atualização
                    treinadorModel.add(linkTo(methodOn(TreinadorController.class).atualizarTreinador(id, treinador)).withRel("update"));

                    // Adiciona link para deletar
                    treinadorModel.add(linkTo(methodOn(TreinadorController.class).deletarTreinador(id)).withRel("delete"));

                    return ResponseEntity.ok(treinadorModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar treinadores por especialidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de treinadores com a especialidade fornecida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treinador.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum treinador encontrado com a especialidade fornecida",
                    content = @Content)
    })
    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<Treinador>> buscarPorEspecialidade(
            @Parameter(description = "Especialidade do treinador a ser buscada") @PathVariable String especialidade) {
        List<Treinador> treinadores = treinadorService.buscarPorEspecialidade(especialidade);
        if (treinadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(treinadores);
    }

    @Operation(summary = "Cadastrar um novo treinador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Treinador cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treinador.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Treinador> salvarTreinador(
            @Parameter(description = "Dados do treinador a ser cadastrado") @RequestBody Treinador treinador) {
        Treinador novoTreinador = treinadorService.salvarTreinador(treinador);
        return ResponseEntity.status(201).body(novoTreinador);
    }

    @Operation(summary = "Atualizar treinador pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treinador atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treinador.class))),
            @ApiResponse(responseCode = "404", description = "Treinador não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Treinador> atualizarTreinador(
            @Parameter(description = "ID do treinador a ser atualizado") @PathVariable Long id,
            @Parameter(description = "Dados do treinador atualizados") @RequestBody Treinador treinadorAtualizado) {

        return treinadorService.buscarPorId(id)
                .map(treinadorExistente -> {
                    // Atualizando os campos necessários
                    treinadorExistente.setNome(treinadorAtualizado.getNome());
                    treinadorExistente.setEmail(treinadorAtualizado.getEmail());
                    treinadorExistente.setEspecialidade(treinadorAtualizado.getEspecialidade());
                    treinadorExistente.setRegistroProf(treinadorAtualizado.getRegistroProf());
                    treinadorExistente.setDataContratacao(treinadorAtualizado.getDataContratacao());
                    // Outros campos que precisarem ser atualizados

                    Treinador treinadorAtualizadoDb = treinadorService.salvarTreinador(treinadorExistente);
                    return ResponseEntity.ok(treinadorAtualizadoDb);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Deletar treinador pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Treinador deletado com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Treinador não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreinador(
            @Parameter(description = "ID do treinador a ser deletado") @PathVariable Long id) {
        treinadorService.deletarTreinador(id);
        return ResponseEntity.noContent().build();
    }
}
