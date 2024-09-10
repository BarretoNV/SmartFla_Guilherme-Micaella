package com.iff.devweb.repository;

import com.iff.devweb.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    @Query("SELECT t FROM Treino t WHERE t.tipoTreino = :tipoTreino")
    List<Treino> findByTipoTreino(@Param("tipoTreino") String tipoTreino);
}
