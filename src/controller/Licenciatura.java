package controller;

import java.util.ArrayList;
import java.util.List;

public class Licenciatura extends Carrera {
    private final String[] materiasPropiasLicenciatura = {
            "Marketing",
            "Matemática Dis",                //Matemática Discreta
            "Testing",                      //Testing de Aplicaciones"
            "Funda Eco",                    //Fundamentos de Economía
            "Gest Pers Tec.",               //Gestión de Personas en Organizaciones de Tecnología
            "Dis Anális Alg",               //Diseño y Análisis de Algoritmos
            "Direc Proy Tec.",              //dirección de Proyectos de Tecnología
            "Liderz Negoci",               //Liderazgo y Negociación
            "Examen Inglés",                //Examen de Inglés
            "Semin Gest Tec.",              //Seminario de Gestión de Tecnología
            "Neg Tec"                       //Negocios Tecnológicos
    };

    public Licenciatura() {
        super("Licenciatura en Gestión IT", 4,4);
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
        return materiasPropiasLicenciatura;
    }
}
