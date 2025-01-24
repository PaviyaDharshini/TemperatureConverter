import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> sourceScale;
    private JComboBox<String> targetScale;
    private JLabel resultLabel;

    // Temperature scales
    private static final String[] SCALES = {"Celsius", "Fahrenheit", "Kelvin"};

    public TemperatureConverter() {
        // Set up the frame
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input field for temperature
        inputField = new JTextField(10);
        add(new JLabel("Enter Temperature:"));
        add(inputField);

        // Source temperature scale selection
        sourceScale = new JComboBox<>(SCALES);
        add(new JLabel("From:"));
        add(sourceScale);

        // Target temperature scale selection
        targetScale = new JComboBox<>(SCALES);
        add(new JLabel("To:"));
        add(targetScale);

        // Convert button
        JButton convertButton = new JButton("Convert");
        add(convertButton);
        
        // Result label
        resultLabel = new JLabel("Result: ");
        add(resultLabel);

        // Action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            // Get input temperature and scales
            double inputTemp = Double.parseDouble(inputField.getText());
            String fromScale = (String) sourceScale.getSelectedItem();
            String toScale = (String) targetScale.getSelectedItem();

            // Perform conversion
            double convertedTemp = performConversion(inputTemp, fromScale, toScale);
            resultLabel.setText(String.format("Result: %.2f %s", convertedTemp, toScale));
        } catch (NumberFormatException e) {
            // Handle invalid number format
            JOptionPane.showMessageDialog(this, "Please enter a valid temperature.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double performConversion(double temp, String fromScale, String toScale) {
        // Convert temperature from source scale to Celsius
        double tempInCelsius;
        switch (fromScale) {
            case "Celsius":
                tempInCelsius = temp;
                break;
            case "Fahrenheit":
                tempInCelsius = (temp - 32) * 5 / 9;
                break;
            case "Kelvin":
                tempInCelsius = temp - 273.15;
                break;
            default:
                throw new IllegalArgumentException("Unknown temperature scale: " + fromScale);
        }

        // Convert Celsius to target scale
        switch (toScale) {
            case "Celsius":
                return tempInCelsius;
            case "Fahrenheit":
                return (tempInCelsius * 9 / 5) + 32;
            case "Kelvin":
                return tempInCelsius + 273.15;
            default:
                throw new IllegalArgumentException("Unknown temperature scale: " + toScale);
        }
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(() -> {
            TemperatureConverter converter = new TemperatureConverter();
            converter.setVisible(true);
        });
    }
}
