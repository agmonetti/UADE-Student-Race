package controller;

import java.util.ArrayList;
import java.util.List;

public class Tecnicatura extends Carrera {
    private final String[] materiasPropiasTecnicatura = {
            "Testing app",     //Testing de Aplicacione
            "Optativa 1",
            "TrIntgr Fnl", //Trabajo Integrador Final
            "Optativa 2"
    };

    public Tecnicatura() {
        super("Tecnicatura en Desarrollo de Software", 3,2);
    }

    @Override
    public String[] getMaterias() {
        List<String> todasLasMaterias = new ArrayList<>();
        for (String materia : materiasGenerales) {
            todasLasMaterias.add(materia);
        }
        for (String materia : getMateriasPropias()) {
            todasLasMaterias.add(materia);
        }
        return todasLasMaterias.toArray(new String[0]);
    }

    public String[] getMateriasPropias() {
        return materiasPropiasTecnicatura;
    }
}
