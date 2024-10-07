package com.iff.devweb.controller.apirest;

import com.iff.devweb.Service.TreinadorService;
import com.iff.devweb.entity.Treinador;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Treinador encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Treinador.class))),
            @ApiResponse(responseCode = "404", description = "Treinador não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Treinador> buscarPorId(
            @Parameter(description = "ID do treinador a ser buscado") @PathVariable Long id) {
        return treinadorService.buscarPorId(id)
                .map(treinador -> ResponseEntity.ok(treinador))
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
