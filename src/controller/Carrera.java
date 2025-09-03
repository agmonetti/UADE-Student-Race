package controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Carrera {
    private String nombreCarrera;
    private int duracionCarrera;
    private int aplazosMaximos;

    protected final String[] materiasGenerales = {
            "Intr Algorit",         //"Introducción a la Algoritmia"
            "Sist Info I",          //Sistemas de Información I"
            "Arq Comput",           //Arquitectura de Computadores"
            "Dis Des Web",       //Diseño y Desarrollo Web"
            "Alg Est Dat I",        //Algoritmos y Estructuras de Datos I"
            "Sist Info II",         //Sistemas de Información II
            "Sist Operat",          //Sistemas Operativos
            "Redes Datos",          //Redes de Datos
            "POO",                  //Paradigma Orientado a Objetos
            "Ing Datos I",          //Ingeniería de Datos I
            "App InteracT",         //Aplicaciones Interactivas
            "Proc Des SW",          //Proceso de Desarrollo de Software
            "Des App I",            //"Desarrollo de Aplicaciones I
            "Prob Estad",           //Probabilidad y Estadística
            "Seg Intgr Info",      //Seguridad e Integridad de la Información
            "Ing Datos II",         //Ingeniería de Datos II
            "Des App II",           //Desarrollo de Aplicaciones II"
            "CC Datos",             //Ciencia de Datos
            "Eval Proy Tec",        //Evaluación de Proyectos de Tecnología
            "TenD Tec",             //"Tendencias Tecnológicas
            "Dcho Info"             //Derecho Informático
    };

    public Carrera(String nombreCarrera, int duracionCarrera, int aplazoMaximo) {
        this.nombreCarrera = nombreCarrera;
        this.duracionCarrera = duracionCarrera;
        this.aplazosMaximos = aplazoMaximo;
    }

    public abstract String[] getMaterias();

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public int getDuracionCarrera() {
        return duracionCarrera;
    }

    public int getAplazosMaximos() {
        return aplazosMaximos;
    }
}
