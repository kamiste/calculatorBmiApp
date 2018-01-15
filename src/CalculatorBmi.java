import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class CalculatorBmi extends JFrame implements ActionListener {

    private JLabel mainLabel, weightLabel, heightLabel;
    private JFormattedTextField weightTxt, heightTxt;
    private JPanel inputPanel, mainPanel;
    private JButton actionButton;
    private double BMI, weight, height;
    private String msg = "";

    public CalculatorBmi() {
        super("Calculator BMI"); // nazwa okna
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // zamykanie okna
        setSize(320, 200); // wielkosc
        setLayout(new FlowLayout());
        setLocationRelativeTo(null); // ustawienie okna na srodku

        // tworze etykiety
        mainLabel = new JLabel("<html><center>Calculator BMI for adult people (age 18+)</center>" +
                "You must have weight>45 and height>150</html>", SwingConstants.CENTER);
        weightLabel = new JLabel("Your weight [kg]: ");
        weightLabel.setPreferredSize(new Dimension(100, 15));
        heightLabel = new JLabel("Your height [cm]: ");
        heightLabel.setPreferredSize(new Dimension(100, 15));

        // tworze pola sformatowane, mozna wprowadzic tylko liczby
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

        // tworze przycisk do obslugi zdarzen
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


    // metoda do pobrania z pola tekstowego wagi
    private void getW() {
        StringTokenizer st = new StringTokenizer(weightTxt.getText(), ",");
        if (st.countTokens() == 2) {
            if (st.hasMoreTokens()) {
                String valueA = st.nextToken();
                String valueB = st.nextToken();
                String value = valueA + "." + valueB;
                weight = Double.parseDouble(value);
            }
        } else {
            weight = Double.parseDouble(weightTxt.getText());
        }
    }

    // metoda do pobrania z pola tekstowego wysokości
    private void getH() {
        StringTokenizer st = new StringTokenizer(heightTxt.getText(), ",");
        if (st.countTokens() == 2) {
            if (st.hasMoreTokens()) {
                String valueA = st.nextToken();
                String valueB = st.nextToken();
                String value = valueA + "." + valueB;
                height = (Double.parseDouble(value) / 100);
            }
        } else {
            height = (Double.parseDouble(heightTxt.getText()) / 100);
        }
    }

    // metoda do obliczenia BMI
    private void calc() {
        BMI = ((weight) / (Math.pow(height, 2)));
    }

    // metoda to wyswietlania rezultatów
    private void results() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        JOptionPane.showMessageDialog(null, "Your's BMI is  " + df.format(BMI) + "  you are " + msg);
    }

    // metoda do sprawdzania poprawnosci wprowadzonych danych
    private boolean isCorrect() {
        boolean correct = true;
        // sprawdzanie czy wprowadzone sa dobre liczby
        if (weightTxt.getText().isEmpty() && heightTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Give your weight and height, just numbers!");
            correct = false;
        } else if (weightTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Give your weight, just numbers!");
            correct = false;
        } else if (heightTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Give your height, just numbers!");
            correct = false;
        }
        return correct;
    }

    // metoda do sprawdzania BMI
    private void checkBMI() {
        if (BMI <= 18) {
            msg = "Underweight";
        } else if (BMI > 18 && BMI <= 24) {
            msg = "Healty";
        } else if (BMI > 24 && BMI <= 29) {
            msg = "Overweight";
        } else if (BMI > 29 && BMI <= 39) {
            msg = "Obese";
        } else if (BMI > 39 && BMI <= 65) {
            msg = "Extremely Obese";
        }
    }

    // MAIN
    public static void main(String[] args) {
        new CalculatorBmi();
    }

    // obsluga zdarzen
    @Override
    public void actionPerformed(ActionEvent e) {

        if (isCorrect()) {
            getW();
            getH();
            if (weight <= 45 || height <= 1.5)
                JOptionPane.showMessageDialog(null, "Your are to young!");
            else {
                calc();
                checkBMI();
                results();
            }
        }

    }
}
