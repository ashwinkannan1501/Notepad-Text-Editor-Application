import javax.swing.*;
import java.awt.*;

public class FrameWindow {
    public static void frame(){
        JFrame frame = new JFrame();
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\ASHWINKANNAN\\OneDrive\\Desktop\\Desktop images\\note.jpg");
        MenuBar menuBar = new MenuBar();
        Text_Area textArea = new Text_Area();

        frame.setSize(550, 650);
        frame.setTitle("Notepad Text Editor Application");
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(textArea.scrollPane);
        frame.setJMenuBar(textArea.menuBar);

        frame.setVisible(true);
    }
}
