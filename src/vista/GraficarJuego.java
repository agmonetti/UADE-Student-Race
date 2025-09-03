package vista;

import controller.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GraficarJuego extends JPanel implements ActionListener, KeyListener {
    private Estudiante estudiante;
    private ArrayList<Materia> materias;
    private Timer timer;
    private int nivelActual;
    private int panelWidth = 800;
    private int panelHeight = 600;
    private boolean juegoTerminado;
    private int velocidadBase = 10;
    private Clip musicClip;
    private Random rand;
    private int tiempoHastaProximaMateria;
    private static final int INTERVALO_MATERIA_MIN = 250; // 1/4 de seg
    private static final int INTERVALO_MATERIA_MAX = 1000; // 1 seg
    private Mapa mapa;
    private Image fondoImagen;
    private JButton botonReiniciar;


    public GraficarJuego() {

        mapa = new Mapa("src/resources/mapa.jpeg");

        fondoImagen = new ImageIcon("src/resources/uade.jpg").getImage();

        setPreferredSize(new Dimension(panelWidth, panelHeight));
        // setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        GraficarLogIn login = new GraficarLogIn();
        estudiante = login.mostrarLogin();

        materias = new ArrayList<>();
        nivelActual = estudiante.getAnio();
        juegoTerminado = false;
        rand = new Random();

        iniciarNivel();
        reproducirMusica();

        timer = new Timer(20, this);
        timer.start();

        setLayout(null);
        botonReiniciar = new JButton("Guardar");
        botonReiniciar.setFont(new Font("Tiny5", Font.BOLD, 16));
        botonReiniciar.setBounds(650, 20, 120, 40);
        botonReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });
        add(botonReiniciar);

    }

    private void iniciarNivel() {
        estudiante.moverJugador(panelWidth / 2, panelHeight - 50);
        materias.clear();
        tiempoHastaProximaMateria = generarTiempoAparicion();
    }

    private int generarTiempoAparicion() {
        return rand.nextInt(INTERVALO_MATERIA_MAX - INTERVALO_MATERIA_MIN + 1) + INTERVALO_MATERIA_MIN;
    }

    private void generarMateria() {
        String[] materiasDisponibles = estudiante.getMaterias();
        String nombreMateria = materiasDisponibles[rand.nextInt(materiasDisponibles.length)];
        int y = rand.nextInt(panelHeight - 150) + 75;
        int velocidad = velocidadBase * (nivelActual * 2);
        materias.add(new Materia(nombreMateria, panelWidth, y, velocidad));
    }
    // Dibujar/Renderizar elementos del juego
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        mapa.dibujar(g);
        estudiante.renderizarMonoChino(g);

        for (Materia materia : materias) {
            materia.dibujar(g2d);
        }


        // Información visible
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Tiny5", Font.PLAIN, 19));
        g2d.drawString("Carrera: " + estudiante.getCarreraNombre(), 10, 20);
        g2d.drawString("Año: " + estudiante.getAnio(), 10, 40);
        g2d.drawString("Aplazos: " + estudiante.getCantidadAplazos(), 10, 60);

        if (juegoTerminado) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            if (estudiante.getCantidadAplazos() >= estudiante.getCarrera().getAplazosMaximos()) {
                String mensaje = "¡Juego Terminado! Aplazos Excedidos";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (panelWidth - fm.stringWidth(mensaje)) / 2;
                int y = panelHeight / 2;
                g2d.drawString(mensaje, x, y);
            } else {
                String mensaje = "¡Felicitaciones! ¡Graduado!";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (panelWidth - fm.stringWidth(mensaje)) / 2;
                int y = panelHeight / 2;
                g2d.drawString(mensaje, x, y);
            }
        }
    }
    // refrescar elementos
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!juegoTerminado) {
            moverMaterias();
            verificarColisiones();
            verificarNivelCompletado();
            manejarAparicionMaterias();
        }
        repaint();
    }

    private void manejarAparicionMaterias() {
        tiempoHastaProximaMateria -= timer.getDelay();
        if (tiempoHastaProximaMateria <= 0) {
            generarMateria();
            tiempoHastaProximaMateria = generarTiempoAparicion();
        }
    }

    private void moverMaterias() {
        for (int i = materias.size() - 1; i >= 0; i--) {
            Materia materia = materias.get(i);
            materia.mover();
            if (materia.fueraDePantalla()) {
                materias.remove(i);
            }
        }
    }

    private void verificarColisiones() {
        Rectangle estudianteRect = new Rectangle(estudiante.getPosicionX(), estudiante.getPosicionY(), 30, 30);

        for (int i = materias.size() - 1; i >= 0; i--) {
            Materia materia = materias.get(i);
            Rectangle materiaRect = new Rectangle(materia.getPosicionX(), materia.getPosicionY(), materia.getAncho(), materia.getAlto());

            if (estudianteRect.intersects(materiaRect)) {
                estudiante.agregarAplazo();
                materias.remove(i);

                if (estudiante.getCantidadAplazos() >= estudiante.getCarrera().getAplazosMaximos()) {
                    juegoTerminado = true;
                } else {
                    estudiante.moverJugador(panelWidth / 2, panelHeight - 50);
                    estudiante.reducirVelocidad();
                }
                break;
            }
        }

        if (mapa.colisiona(estudianteRect)) {
            // ☠️☠️☠️☠️☠️
            estudiante.moverJugador(panelWidth / 2, panelHeight - 50);
        }

        // Verificar si el estudiante llego a la META
        if (mapa.esPuertaNivel(estudianteRect)) {
            nivelActual++;
            estudiante.avanzarAnio();
            if (nivelActual > estudiante.getCarrera().getDuracionCarrera()) {
                if (estudiante.getCantidadAplazos() < estudiante.getCarrera().getAplazosMaximos()) {
                    juegoTerminado = true;
                    RegistroAlumnos.registroAlumno(estudiante);
                }
            } else {
                iniciarNivel();
            }
        }
    }

    private void verificarNivelCompletado() {
        if (estudiante.getPosicionY() <= 0) {
            nivelActual++;
            estudiante.avanzarAnio();
            if (nivelActual > estudiante.getCarrera().getDuracionCarrera()) {
                if (estudiante.getCantidadAplazos() < estudiante.getCarrera().getAplazosMaximos()) {
                    juegoTerminado = true;
                    RegistroAlumnos.registroAlumno(estudiante);
                }
            } else {
                iniciarNivel();
            }
        }
    }

    // tiki tiki tiki
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int x = estudiante.getPosicionX();
        int y = estudiante.getPosicionY();
        int velocidad = estudiante.getVelocidadAlumno();

        if (key == KeyEvent.VK_A) x -= velocidad;
        if (key == KeyEvent.VK_D) x += velocidad;
        if (key == KeyEvent.VK_W) y -= velocidad;
        if (key == KeyEvent.VK_S) y += velocidad;

        x = Math.max(0, Math.min(x, panelWidth - 30));
        y = Math.max(0, Math.min(y, panelHeight - 30));

        estudiante.moverJugador(x, y);
    }

    private void reiniciarJuego() {

        RegistroAlumnos.registroAlumno(estudiante);

        // Cerrar la ventana actual
        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (ventanaActual != null) {
            ventanaActual.dispose();
        }
        GraficarJuego nuevoJuego = new GraficarJuego();


        JFrame nuevaVentana = new JFrame("UADE STUDENT RACE");
        nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nuevaVentana.add(nuevoJuego);
        nuevaVentana.pack();
        nuevaVentana.setLocationRelativeTo(null);
        nuevaVentana.setVisible(true);
    }

    // 🎶☠️🎶
    private void reproducirMusica() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/resources/bg_music.wav"));
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }
//
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
