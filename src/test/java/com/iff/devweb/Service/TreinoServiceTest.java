package com.iff.devweb.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.iff.devweb.Service.TreinoService;
import com.iff.devweb.entity.CdTipoTreinoEnum;
import com.iff.devweb.entity.Treino;
import com.iff.devweb.repository.TreinoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class TreinoServiceTest {

    @Mock
    private TreinoRepository treinoRepository;

    @InjectMocks
    private TreinoService treinoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarTreino() {
        Treino treino = new Treino();
        treino.setTipoTreino(CdTipoTreinoEnum.Musculacao);

        when(treinoRepository.save(any(Treino.class))).thenReturn(treino);

        Treino result = treinoService.salvarTreino(treino);
        assertNotNull(result);
        assertEquals(CdTipoTreinoEnum.Musculacao, result.getTipoTreino());
        verify(treinoRepository, times(1)).save(treino);
    }

    @Test
    public void testBuscarPorId() {
        Treino treino = new Treino();
        treino.setId(1L);

        when(treinoRepository.findById(1L)).thenReturn(Optional.of(treino));

        Optional<Treino> result = treinoService.buscarPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(treinoRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeletarTreino() {
        doNothing().when(treinoRepository).deleteById(1L);

        treinoService.deletarTreino(1L);
        verify(treinoRepository, times(1)).deleteById(1L);
    }
}