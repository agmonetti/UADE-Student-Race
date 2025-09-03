package vista;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class GraficarLogIn {
    private Image fondoImagen;
    private Estudiante estudiante;

    public GraficarLogIn() {
        fondoImagen = new ImageIcon("src/resources/uade.jpg").getImage();
    }

    public Estudiante mostrarLogin() {
        JPanel panelEntrada = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;


                g2d.drawImage(fondoImagen, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelEntrada.setLayout(new GridBagLayout());
        panelEntrada.setPreferredSize(new Dimension(500, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelEntrada.add(labelNombre, gbc);

        JTextField campoNombre = new JTextField();
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panelEntrada.add(campoNombre, gbc);

        JLabel labelCarrera = new JLabel("Carrera:");
        labelCarrera.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelEntrada.add(labelCarrera, gbc);

        String[] carreras = {"Ingeniería", "Licenciatura", "Tecnicatura"};
        JComboBox<String> comboCarrera = new JComboBox<>(carreras);
        comboCarrera.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelEntrada.add(comboCarrera, gbc);


        int resultado = JOptionPane.showConfirmDialog(
                null,
                panelEntrada,
                "Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Verificar LogIn

        if (resultado == JOptionPane.OK_OPTION) {
            String nombreEstudiante = campoNombre.getText();
            String carreraSeleccionada = (String) comboCarrera.getSelectedItem();

            // Validar que el nombre no esté vacío
            if (nombreEstudiante.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para continuar.");
                System.exit(0); // Salir del juego si no hay nombre
            }

            Carrera carrera;
            switch (carreraSeleccionada) {
                case "Ingeniería":
                    carrera = new Ingenieria();
                    break;
                case "Licenciatura":
                    carrera = new Licenciatura();
                    break;
                case "Tecnicatura":
                    carrera = new Tecnicatura();
                    break;
                default:
                    carrera = new Ingenieria(); // Predeterminado
            }
            estudiante = new Estudiante(carrera, nombreEstudiante);
        } else {
            System.exit(0);
        }

        return estudiante;
    }

}

