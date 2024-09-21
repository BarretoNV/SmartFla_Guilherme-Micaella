package com.iff.devweb.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.iff.devweb.Service.TreinadorService;
import com.iff.devweb.entity.Treinador;
import com.iff.devweb.repository.TreinadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class TreinadorServiceTest {

    @Mock
    private TreinadorRepository treinadorRepository;

    @InjectMocks
    private TreinadorService treinadorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarTreinador() {
        Treinador treinador = new Treinador();
        treinador.setNome("Carlos");

        when(treinadorRepository.save(any(Treinador.class))).thenReturn(treinador);

        Treinador result = treinadorService.salvarTreinador(treinador);
        assertNotNull(result);
        assertEquals("Carlos", result.getNome());
        verify(treinadorRepository, times(1)).save(treinador);
    }

    @Test
    public void testBuscarPorId() {
        Treinador treinador = new Treinador();
        treinador.setId(1L);

        when(treinadorRepository.findById(1L)).thenReturn(Optional.of(treinador));

        Optional<Treinador> result = treinadorService.buscarPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(treinadorRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeletarTreinador() {
        doNothing().when(treinadorRepository).deleteById(1L);

        treinadorService.deletarTreinador(1L);
        verify(treinadorRepository, times(1)).deleteById(1L);
    }
}