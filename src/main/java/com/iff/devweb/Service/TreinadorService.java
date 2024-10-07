package com.iff.devweb.Service;

import com.iff.devweb.entity.Treinador;
import com.iff.devweb.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinadorService {

    @Autowired
    private TreinadorRepository treinadorRepository;

    public List<Treinador> listarTodosTreinadores() {
        return treinadorRepository.findAll();
    }

    public Optional<Treinador> buscarPorId(Long id) {
        return treinadorRepository.findById(id);
    }

    public List<Treinador> buscarPorEspecialidade(String especialidade) {
        return treinadorRepository.findByEspecialidade(especialidade);
    }

    public Treinador salvarTreinador(Treinador treinador) {
        return treinadorRepository.save(treinador);
    }

    public void deletarTreinador(Long id) {
        treinadorRepository.deleteById(id);
    }
}
