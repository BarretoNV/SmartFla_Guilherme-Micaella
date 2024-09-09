package com.iff.devweb.repository;

import com.iff.devweb.entity.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TreinadorRepository extends JpaRepository<Treinador, Long> {

    @Query("SELECT t FROM Treinador t WHERE t.especialidade = :especialidade")
    List<Treinador> findByEspecialidade(@Param("especialidade") String especialidade);
}
