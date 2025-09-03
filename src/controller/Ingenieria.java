package controller;

import java.util.ArrayList;
import java.util.List;

public class Ingenieria extends Carrera {
    private final String[] materiasPropiasIngenieria = {
            "Fund Infor",           //Fundamentos de Informática
            "Pnsto Crít Com.",      //Pensamiento Crítico y Comunicación
            "Teoría Sist",          //Teoría de Sistemas
            "Elemt Álg Geo",        //Elementos de Álgebra y Geometría
            "Fund Química",         //Fundamentos de Química
            "Siste Reprnt",         //Sistemas de Representación
            "Matemática Dis",       //Matemática Discreta
            "Álgebra",
            "Física I",
            "Cálculo I",
            "Fund Telecom.",        //Fundamentos de Telecomunicaciones
            "Cálculo II",
            "Teleinf Redes",       //Teleinformática y Redes
            "Física II",
            "Teoría Comput",        //Teoría de la Computación
            "Modldo Simul",    //Modelado y Simulación
            "Inteli Arti",          //Inteligencia Artificial
            "Tec Med Ambie",     //Tecnología y Medio Ambiente
            "Optativa 1",
            "Arq App",              //Arquitectura de Aplicaciones
            "Calidad SW",           //Calidad de Software
            "Optativa 2",
            "Neg Internac",         //Negocios Internacionales
            "Optativa 3",
            "Optativa 4"
    };

    public Ingenieria() {
        super("Ingeniería en Informática", 5,5);
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
        return  materiasPropiasIngenieria;
    }

}
