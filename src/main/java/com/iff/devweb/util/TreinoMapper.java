package com.iff.devweb.util;

import com.iff.devweb.dto.TreinoRequest;
import com.iff.devweb.entity.Treino;
import org.springframework.stereotype.Component;

@Component
public class TreinoMapper {

    public TreinoRepresentation toRepresentation(Treino treino) {
        return new TreinoRepresentation(
                treino.getNomeTreino(),
                treino.getDescricao(),
                treino.getDuracao(),
                treino.getData(),
                treino.getNivelDificuldade().name()
        );
    }
}
