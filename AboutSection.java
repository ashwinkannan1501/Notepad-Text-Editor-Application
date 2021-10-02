// Import the necessary packages

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AboutSection implements ActionListener {
    JButton button;
    JDialog dialogBox;
    AboutSection() {
        String text = "Ashwin is the creator of this basic Notepad Text Editor Application using java on 21/08/2021";

        JLabel label = new JLabel(text);
        label.setFont(new Font("Times new roman", Font.ROMAN_BASELINE, 15));

        button = new JButton("OK");
        button.setFont(new Font("Times new roman", Font.ITALIC, 30));
        button.setFocusable(false);
        button.addActionListener(this::actionPerformed);

        dialogBox = new JDialog();
        dialogBox.setTitle("About Notepad Text Editor");
        dialogBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogBox.setLocationRelativeTo(null);
        dialogBox.setLayout(new FlowLayout());
        dialogBox.add(label);
        dialogBox.add(button);
        dialogBox.setVisible(true);
        dialogBox.pack();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button) {
            dialogBox.setVisible(false);
        }
    }
}