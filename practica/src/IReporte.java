import javax.swing.*;
import java.awt.event.*;

class RadioButton extends JRadioButton {
    public RadioButton(String text, int x, int y, RadioButtonGroup group) {
        super(text);
        setBounds(x, y, 150, 30);
        group.addRadioButton(this);
    }
}

class RadioButtonGroup {
    private ButtonGroup buttonGroup;
    private String selectedRadioButton;

    public RadioButtonGroup() {
        buttonGroup = new ButtonGroup();
        selectedRadioButton = "";
    }

    public void addRadioButton(RadioButton radioButton) {
        buttonGroup.add(radioButton);
        radioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedRadioButton = radioButton.getText();
            }
        });
    }

    public String getSelectedRadioButton() {
        return selectedRadioButton;
    }
}

class Etiqueta extends JLabel {
    public Etiqueta(String texto, int x, int y) {
        setText(texto);
        setBounds(x, y, 200, 30);
    }
}

class Frame extends JFrame {
    JPanel panel;
    private RadioButtonGroup radioButtonGroup;

    public Frame(String titulo) {
        // Ventana
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(titulo);
        setLocation(100, 200);
        setLocationRelativeTo(null);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 50, 800, 350);

        radioButtonGroup = new RadioButtonGroup();

        RadioButton radio1 = new RadioButton("Individual", 250, 10, radioButtonGroup);
        RadioButton radio2 = new RadioButton("General", 400, 10, radioButtonGroup);

        radio1.addActionListener(e -> updatePanel("Individual"));
        radio2.addActionListener(e -> updatePanel("General"));

        add(radio1);
        add(radio2);

        Etiqueta lb = new Etiqueta("Tipo de reporte:", 10, 10);
        add(lb);

        add(panel);
    }

    private void updatePanel(String type) {
        panel.removeAll(); 

        if (type.equals("Individual")) {
            JLabel label = new JLabel("C.I. del responsable del equipo");
            JTextField textField = new JTextField();
            JButton button = new JButton("Totalizar");
            label.setBounds(50, 50, 200, 30);
            textField.setBounds(250, 50, 200, 28);
            button.setBounds(480, 50, 100, 30);
            
            panel.add(label);
            panel.add(textField);
            panel.add(button);
            JLabel label1 = new JLabel("Totalización:");
            JLabel label2 = new JLabel("equipos");
            JLabel label3 = new JLabel("bs");
            label1.setBounds(50, 150, 200, 30);
            label2.setBounds(90, 190, 200, 30);
            label3.setBounds(90, 230, 200, 30);
            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
        } else if (type.equals("General")) {
            JTextArea textArea = new JTextArea(20, 30);
            JScrollPane scroll = new JScrollPane(textArea);
            scroll.setBounds(100,30,600,250);
            //panel.add(textArea);
            JLabel label1 = new JLabel("Ci del representante:");
            JLabel label2 = new JLabel("Equipos:");
            JLabel label3 = new JLabel("Monto bs:");
            label1.setBounds(110, 30, 200, 30);
            label2.setBounds(370, 30, 200, 30);
            label3.setBounds(600, 30, 200, 30);
            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(scroll);
           
        }
        panel.revalidate(); 
        panel.repaint(); 
    }


}

public class IReporte {
    public static void main(String[] args) {
        Frame v1 = new Frame("Reporte del inventario del Centro de Investigación");
        v1.setVisible(true);
    }
}
