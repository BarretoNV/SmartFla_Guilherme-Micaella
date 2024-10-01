package com.iff.devweb.controller.apirest;

import com.iff.devweb.Service.TreinoService;
import com.iff.devweb.entity.Treino;
import com.iff.devweb.exception.TreinoDurationExceededException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @Operation(summary = "Listar todos os treinos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todos os treinos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treino.class)))
    })
    @GetMapping
    public ResponseEntity<List<Treino>> listarTodosTreinos() {
        List<Treino> treinos = treinoService.listarTodosTreinos();
        return ResponseEntity.ok(treinos);
    }

    @Operation(summary = "Buscar treino pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treino encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treino.class))),
            @ApiResponse(responseCode = "404", description = "Treino não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Treino> buscarTreinoPorId(
            @Parameter(description = "ID do treino a ser buscado") @PathVariable Long id) {
        Optional<Treino> treino = treinoService.buscarPorId(id);
        if (treino.isPresent()) {
            return ResponseEntity.ok(treino.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar treinos por tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de treinos com o tipo fornecido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treino.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum treino encontrado com o tipo fornecido",
                    content = @Content)
    })
    @GetMapping("/tipo/{tipoTreino}")
    public ResponseEntity<List<Treino>> buscarTreinoPorTipo(
            @Parameter(description = "Tipo do treino a ser buscado") @PathVariable String tipoTreino) {
        List<Treino> treinos = treinoService.buscarPorTipoTreino(tipoTreino);
        return ResponseEntity.ok(treinos);
    }

    @Operation(summary = "Cadastrar um novo treino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Treino cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treino.class))),
            @ApiResponse(responseCode = "400", description = "Duração do treino excede o limite de 8 horas",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<String> salvarTreino(
            @Parameter(description = "Dados do treino a ser cadastrado") @RequestBody Treino treino) {
        try {
            treinoService.validarDuracaoTreino(treino);
            treinoService.salvarTreino(treino);
            return new ResponseEntity<>("Treino salvo com sucesso!", HttpStatus.CREATED);
        } catch (TreinoDurationExceededException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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
