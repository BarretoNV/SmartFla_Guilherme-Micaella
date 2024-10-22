package com.iff.devweb.Service;

import com.iff.devweb.entity.Aluno;
import com.iff.devweb.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public List<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizarAluno(Long id, Aluno alunoAtualizado) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNome(alunoAtualizado.getNome());
                    aluno.setEmail(alunoAtualizado.getEmail());
                    aluno.setDataNasc(alunoAtualizado.getDataNasc());
                    aluno.setTel(alunoAtualizado.getTel());
                    aluno.setEndereco(alunoAtualizado.getEndereco());
                    // Atualize outros campos necessários
                    return alunoRepository.save(aluno);
                })
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));
    }


    public void deletarAluno(Long id) {
        alunoRepository.deleteById(id);
    }
}
