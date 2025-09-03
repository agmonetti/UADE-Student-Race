package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistroAlumnos {

    static public void registroAlumno(Estudiante est) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("REGISTRO_ALUMNOS.txt", true)))) {
            writer.println("Nombre: " + est.getNombre());
            writer.println("Carrera: " + est.getCarreraNombre());
            writer.println("Duración de la carrera: " + est.getCarrera().getDuracionCarrera() + " años");
            writer.println("Aplazos usados: " + est.getCantidadAplazos() + " de " + est.getCarrera().getDuracionCarrera());
            writer.println("Llegaste hasta el año: " + est.getAnio() + "");
            writer.println("--------------------------");
        } catch (IOException e) {
            System.out.println("Error al guardar historial de graduado: " + e.getMessage());
        }
    }

}
