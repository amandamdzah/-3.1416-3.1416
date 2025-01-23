import javax.swing.*;
import java.awt.event.*;
import java.io.*;

class Equipo {
    private String descripcion;
    private int cantidad;
    private double costo;
    private String fechaAdquisicion;
    private String numeroFactura;
    private String ciRepresentante;

    public Equipo(String descripcion, int cantidad, double costo, String fechaAdquisicion, String numeroFactura, String ciRepresentante) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.costo = costo;
        this.fechaAdquisicion = fechaAdquisicion;
        this.numeroFactura = numeroFactura;
        this.ciRepresentante = ciRepresentante;
    }
    public String getCiRepresentante() {
        return ciRepresentante;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getCosto() {
        return costo;
    }
    public String toString() {
        return "Descripción: " + descripcion + "\n" +
               "Cantidad: " + cantidad + "\n" +
               "Costo unitario (bs): " + costo + "\n" +
               "Fecha de adquisición: " + fechaAdquisicion + "\n" +
               "Nro. Factura: " + numeroFactura + "\n" +
               "C.I. del representante del equipo: " + ciRepresentante + "\n" +
               "--------\n";
    }
}

class Ventana extends JFrame implements ActionListener {
    private JButton botonSalir, botonRegistrar, botonReporte;
    private CuadroDeTexto cDescrip, cCant, cCosto, cFecha, cFactura, cCi;

    public Ventana(String titulo) {
        // Ventana
        setSize(800, 400); // (ancho, largo)
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cuando cierre la ventana el programa finalizara
        setTitle(titulo); // titulo de la ventana
        setLocation(100, 200); // localizacion de la ventana
        setLocationRelativeTo(null); // centra la localizacion de la pantalla 
        setLayout(null);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds(630, 200, 60, 30);
        botonRegistrar = new JButton("Registrar data");
        botonRegistrar.setBounds(500, 200, 120, 30);
        botonReporte = new JButton("Generar reporte");
        botonReporte.setBounds(350, 200, 140, 30);
        add(botonSalir);
        add(botonRegistrar);
        add(botonReporte);

        botonSalir.addActionListener(this);
        botonRegistrar.addActionListener(this);
        botonReporte.addActionListener(this);

        agregarComponentes();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonSalir) {
            System.exit(0);
        }
        if (e.getSource() == botonRegistrar) {
            guardarDatos();
        }
        if (e.getSource() == botonReporte) {
            Frame reporteFrame = new Frame("Reporte del inventario del Centro de Investigación");
            reporteFrame.setVisible(true);
        }
    }

    private void guardarDatos() {
        try (FileWriter writer = new FileWriter("datos.txt", true)) {
            Equipo equipo = new Equipo(
                cDescrip.getText(),
                Integer.parseInt(cCant.getText()),
                Double.parseDouble(cCosto.getText()),
                cFecha.getText(),
                cFactura.getText(),
                cCi.getText()
            );

            writer.write(equipo.toString());
            writer.flush();
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente.");
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos.");
        }
    }

    private void agregarComponentes() {
        Etiqueta lb = new Etiqueta("Ingrese data del equipo:", 10, 10);
        Etiqueta lbDescripcion = new Etiqueta("Descripción: ", 20, 40);
        cDescrip = new CuadroDeTexto("", 150, 40, 600, 25);
        Etiqueta lbCantidad = new Etiqueta("Cantidad: ", 28, 80);
        cCant = new CuadroDeTexto("", 150, 80, 50, 25);
        Etiqueta lbCosto = new Etiqueta("Costo unitario (bs): ", 220, 80);
        cCosto = new CuadroDeTexto("", 350, 80, 400, 25);
        Etiqueta lbFecha = new Etiqueta("Fecha de adquisición: ", 20, 120);
        cFecha = new CuadroDeTexto("", 150, 120, 150, 25);
        Etiqueta lbFactura = new Etiqueta("Nro. Factura: ", 320, 120);
        cFactura = new CuadroDeTexto("", 400, 120, 350, 25);
        Etiqueta lbFormato = new Etiqueta("dd/mm/aaaa: ", 20, 130);
        Etiqueta lbCi = new Etiqueta("C.I. del representante del equipo: ", 20, 160);
        cCi = new CuadroDeTexto("", 210, 160, 200, 25);

        add(lb);
        add(lbDescripcion);
        add(cDescrip);
        add(lbCantidad);
        add(cCant);
        add(lbCosto);
        add(cCosto);
        add(lbFecha);
        add(cFecha);
        add(lbFactura);
        add(cFactura);
        add(lbFormato);
        add(lbCi);
        add(cCi);
    }
}

class Etiqueta extends JLabel {
    public Etiqueta(String texto, int x, int y) {
        setText(texto);
        setBounds(x, y, 200, 30);
    }
}

class CuadroDeTexto extends JTextField {
    public CuadroDeTexto(String texto, int x, int y, int ancho, int alto) {
        setBounds(x, y, ancho, alto);
    }
}

class Boton extends JButton {
    public Boton(String texto, int x, int y, int ancho, int alto) {
        setText(texto);
        setBounds(x, y, ancho, alto);
    }
}

public class ICentro {
    public static void main(String[] args) {
        Ventana v1 = new Ventana("Registro y control de equipos en centro de investigación");
        v1.setVisible(true);
    }
}

