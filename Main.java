import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {
    private JTextField inputField;

    private JLabel resultLabel;
    public Main() {
        setTitle("Weather App");
        setLayout(new BorderLayout());

        setSize(400, 200);

        // Input panel with a text field and a button
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(10);
        JButton button = new JButton("Search");
        inputPanel.add(inputField);
        inputPanel.add(button);

        // Add the input panel to the frame
        add(inputPanel, BorderLayout.NORTH);

        // Result panel with a label
        JPanel resultPanel = new JPanel();
        resultLabel = new JLabel();
        resultPanel.add(resultLabel);

        add(resultPanel, BorderLayout.SOUTH);

        // Action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input from the text field
                String input = inputField.getText();
                String response;
                try {
                    response = WeatherAPI.getTemperatureAtLocation(input); // Calling WeatherAPI
                    //response
                } catch (Exception error)
                {response = error.getMessage();
                }
                // Display the input in the result label
                resultLabel.setText(response + "°C");
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
