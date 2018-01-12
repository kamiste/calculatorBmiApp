import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class CalculatorBmi extends JFrame implements ActionListener {

    private JLabel mainLabel, weightLabel, heightLabel;
    private JFormattedTextField weightTxt, heightTxt;
    private JPanel inputPanel, mainPanel;
    private JButton actionButton;
    private double BMI, weight, height;

    public CalculatorBmi() {
        super("Calculator BMI"); // nazwa okna
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // zamykanie okna
        setSize(320, 200); // wielkosc
        setLayout(new FlowLayout());
        setLocationRelativeTo(null); // ustawienie okna na srodku

        // tworze etykiety
        mainLabel = new JLabel("<html><center>Enter your data and calculate BMI !</center>You must have weight>45 and height>150</html>", SwingConstants.CENTER);
        weightLabel = new JLabel("Your weight [kg]: ");
        weightLabel.setPreferredSize(new Dimension(100, 15));
        heightLabel = new JLabel("Your height [cm]: ");
        heightLabel.setPreferredSize(new Dimension(100, 15));

        // tworze pola tekstowe
        weightTxt = new JFormattedTextField(NumberFormat.getNumberInstance());
        weightTxt.setPreferredSize(new Dimension(25, 15));
        heightTxt = new JFormattedTextField(NumberFormat.getNumberInstance());
        heightTxt.setPreferredSize(new Dimension(25, 15));

        // tworze panel to wprowadzania danych
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 2, 2));
        inputPanel.setPreferredSize(new Dimension(200, 40));

        // dodaje wszystko do panelu
        inputPanel.add(weightLabel);
        inputPanel.add(weightTxt);
        inputPanel.add(heightLabel);
        inputPanel.add(heightTxt);

        // tworze przycisk do obliczen
        actionButton = new JButton("Calculate!");
        actionButton.setPreferredSize(new Dimension(150, 20));
        actionButton.addActionListener(this);

        // tworze panel do wysrodkowania
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // dodaje wszystko do glownego panelu
        mainPanel.add(mainLabel, BorderLayout.PAGE_START);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(actionButton, BorderLayout.PAGE_END);

        this.add(mainPanel);

        this.setVisible(true); // widoczność
    }

    private void getW() {
        weight = Double.parseDouble(weightTxt.getText());
        System.out.println("getW");
    }

    private void getH() {
        height = (Double.parseDouble(heightTxt.getText()) / 100);
        System.out.println("getH");
    }

    private void calc() {
        System.out.println("calc");
        BMI = ((weight) / (Math.pow(height, 2)));
    }

    private void results() {
        System.out.println("results");
        JOptionPane.showMessageDialog(null, "Your's BMI : " + BMI);
    }

    public static void main(String[] args) {
        new CalculatorBmi();
    }

    // obsluga zdarzen
    @Override
    public void actionPerformed(ActionEvent e) {

        //JButton actButton = (JButton)(e.getSource());

        if (weightTxt.getText().isEmpty() && heightTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Give your weight and height, just numbers!");
        } else if (weightTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Give your weight, just numbers!");
        } else if (heightTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Give your height, just numbers!");
        } else {
            getW();
            getH();
            if (weight <= 45 || height <= 1.5)
                JOptionPane.showMessageDialog(null, "Your are to young!");
            else {
                calc();
                results();
            }
        }

    }
}
