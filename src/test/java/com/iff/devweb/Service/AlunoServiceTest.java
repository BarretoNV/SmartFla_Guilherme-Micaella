package com.iff.devweb.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.iff.devweb.Service.AlunoService;
import com.iff.devweb.entity.Aluno;
import com.iff.devweb.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Maria");
        aluno.setId(1L);

        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        Aluno result = alunoService.salvarAluno(aluno);
        assertNotNull(result);
        assertEquals("Maria", result.getNome());
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    public void testBuscarPorId() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        Optional<Aluno> result = alunoService.buscarPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(alunoRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeletarAluno() {
        doNothing().when(alunoRepository).deleteById(1L);

        alunoService.deletarAluno(1L);
        verify(alunoRepository, times(1)).deleteById(1L);
    }
}