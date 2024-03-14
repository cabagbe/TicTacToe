import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    private boolean xTurn = true;
    private JFrame frame;
    private JPanel panel;
    private final JButton[] buttons = new JButton[9];

    public TicTacToe() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        // Creating panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));

        for (int i = 0; i<9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        // Adding panel to frame
        frame.add(panel);
        // Make the frame visible
        frame.setVisible(true);

    }

    public void checkWinner() {
        // Check the Rows
        for(int i = 0; i < 9; i += 3) {
            if(buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && !buttons[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
                reset();
                return;
            }
        }

        // Check for Columns
        for (int i = 0; i < 3; i++) {
            if(buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText()) && !buttons[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
                reset();
                return;
            }
        }
        // Check for Diagonals

        if (!buttons[4].getText().isEmpty()) {

            if(buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[0].getText() + " wins!");
                reset();
                return;
            }

            if(buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[2].getText() + " wins!");
                reset();
                return;
            }


            boolean tie = true;


            for (int i = 0; i<9; i++) {

                if (buttons[i].isEnabled())   {

                    tie = false;
                    break;
                }

                if (tie) {
                    JOptionPane.showMessageDialog(frame, "It's a tie!");
                    reset();
                }

            }

        }


    }

    public void reset() {
        for(int i = 0; i < 9;  i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        xTurn = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setText(xTurn ? "X" : "O");
        button.setEnabled(false);
        xTurn =! xTurn;
        checkWinner();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

}