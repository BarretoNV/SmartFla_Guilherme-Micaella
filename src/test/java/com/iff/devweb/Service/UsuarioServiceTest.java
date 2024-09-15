package com.iff.devweb.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.iff.devweb.Service.UsuarioService;
import com.iff.devweb.entity.Usuario;
import com.iff.devweb.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@email.com");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.salvarUsuario(usuario);
        assertNotNull(result);
        assertEquals("usuario@email.com", result.getEmail());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testBuscarPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.buscarPorId(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeletarUsuario() {
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.deletarUsuario(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}