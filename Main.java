package No;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    private int randomNumber;
    private JTextField guessInput;
    private JLabel feedbackLabel;
    private JButton guessButton;
    private int maxAttempts =10;
    private int attemptCount = 0;

    public Main(){
        Random rand = new Random();
        randomNumber = rand.nextInt(100) +1;

        setTitle("Guess The Number");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));

        JPanel inputPanel = new JPanel();
        JLabel promptLabel = new JLabel("Enter a number Between 1 to 100");
        guessInput = new JTextField(10);
        guessButton = new JButton("guess");
        inputPanel.add(promptLabel);
        inputPanel.add(guessInput);
        inputPanel.add(guessButton);

        feedbackLabel = new JLabel("You have "+ maxAttempts +" Attempts left.",SwingConstants.CENTER);
        add(inputPanel);
        add(feedbackLabel);

        guessButton.addActionListener(new GuessHandler());

        setVisible(true);

    }

    private class GuessHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                int userGuess = Integer.parseInt(guessInput.getText());
                attemptCount++;
                
                if(userGuess<1 || userGuess >100){
                    feedbackLabel.setText("Please enter a number between 1 and 100.");
                }
                else if(userGuess == randomNumber){
                    feedbackLabel.setText("Congratulations! You guessed the number in "+attemptCount+" attempts");
                    guessButton.setEnabled(false);
                }else if(userGuess < randomNumber){
                    feedbackLabel.setText("Too low! Attempts left: "+(maxAttempts - attemptCount));
                }else{
                    feedbackLabel.setText("Too high! Attempts left: "+(maxAttempts - attemptCount));
                }

                if(attemptCount >= maxAttempts && userGuess != randomNumber){
                    feedbackLabel.setText("Game over! The number was "+ randomNumber + " .");
                    guessButton.setEnabled(false);
                }

            }   catch(NumberFormatException ex){
                feedbackLabel.setText("Invalid input. Please enter a valid number.");
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

}