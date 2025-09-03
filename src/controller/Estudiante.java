package controller;

import javax.swing.*;
import java.awt.*;

public class Estudiante {
    private Carrera carrera;
    private java.util.ArrayList<String> materiasAprobadas;
    private int cantidadAplazos;
    private int anio;
    private int velocidadAlumno;
    private int posicionX;
    private int posicionY;
    private Image imagen;
    private String nombre;

    public Estudiante(Carrera carrera, String nombre) {
        this.imagen = new ImageIcon("src/resources/student_boy.png").getImage();
        this.carrera = carrera;
        this.materiasAprobadas = new java.util.ArrayList<>();
        this.cantidadAplazos = 0;
        this.anio = 1;
        this.velocidadAlumno = 4;
        this.posicionX = 0;
        this.posicionY = 0;
        this.nombre = nombre;
    }

    public void moverJugador(int posX, int posY) {
        this.posicionX = posX;
        this.posicionY = posY;
    }

    public void renderizarMonoChino(Graphics g) {
        g.drawImage(imagen, posicionX, posicionY, 30, 30, null);
    }

    public String getCarreraNombre() {
        return carrera.getNombreCarrera();
    }

    public java.util.ArrayList<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public int getCantidadAplazos() {
        return cantidadAplazos;
    }

    public void agregarAplazo() {
        this.cantidadAplazos++;
    }

    public int getCantidadMateriasAprobadas() {
        return materiasAprobadas.size();
    }

    public int getTotalMaterias() {
        return carrera.getMaterias().length;
    }

    public boolean finCarrera() {
        return getCantidadMateriasAprobadas() + cantidadAplazos >= getTotalMaterias();
    }

    public void avanzarAnio() {
        anio++;
    }

    public int getAnio() {
        return anio;
    }

    public String[] getMaterias() {
        return carrera.getMaterias();
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void reducirVelocidad() {
        this.velocidadAlumno = Math.max(1, this.velocidadAlumno - 1);
    }

    public int getVelocidadAlumno() {
        return velocidadAlumno;
    }

    public String getNombre() { return nombre; }

}