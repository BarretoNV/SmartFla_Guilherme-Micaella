package com.iff.devweb.Service;

import com.iff.devweb.entity.Treino;
import com.iff.devweb.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public List<Treino> listarTodosTreinos() {
        return treinoRepository.findAll();
    }

    public Optional<Treino> buscarPorId(Long id) {
        return treinoRepository.findById(id);
    }

    public List<Treino> buscarPorTipoTreino(String tipoTreino) {
        return treinoRepository.findByTipoTreino(tipoTreino);
    }

    public Treino salvarTreino(Treino treino) {
        return treinoRepository.save(treino);
    }

    public void deletarTreino(Long id) {
        treinoRepository.deleteById(id);
    }
}
