// Import the necessary packages
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;

public class Text_Area implements ActionListener, ChangeListener{
    Font font = new Font("Times new roman", Font.BOLD, 20);
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);

    JMenuBar menuBar;
    JMenu fileMenu, aboutMenu, settingsMenu;
    JMenuItem newWindowFile, openFile, saveFile, exitFile, aboutMenuItem, settingsMenuItem;

    JFileChooser fileChooser = new JFileChooser("C:\\Users\\ASHWINKANNAN\\OneDrive\\Desktop");
    File file;
    FileReader fileReader;
    FileWriter fileWriter;
    FileNameExtensionFilter textFile = new FileNameExtensionFilter("Text file", "txt");
    FileNameExtensionFilter pythonFile = new FileNameExtensionFilter("Python file", "py");
    FileNameExtensionFilter javaFile = new FileNameExtensionFilter("Java file", "java");
    FileNameExtensionFilter javaScriptFile = new FileNameExtensionFilter("Java Script file", "js");
    FileNameExtensionFilter htmlFile = new FileNameExtensionFilter("HTML file", "html");
    FileNameExtensionFilter cssFile = new FileNameExtensionFilter("CSS file", "css");

    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    JComboBox<String> comboBox;
    JSpinner spinner;
    JButton fontColorButton, backgroundColorButton;
    JDialog dialogBox;

    Text_Area() {
        textArea.setFont(new Font("Times new roman", Font.PLAIN, 35));

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        menuBar = new JMenuBar();
        menuBar.setFont(font);

        fileMenu = new JMenu("File", true);
        fileMenu.setFont(font);

        newWindowFile = new JMenuItem("New Window (Alt + N)", KeyEvent.VK_N);
        newWindowFile.setFont(font);
        newWindowFile.addActionListener(this::actionPerformed);

        openFile = new JMenuItem("Open (Alt + O)", KeyEvent.VK_O);
        openFile.setFont(font);
        openFile.addActionListener(this::actionPerformed);

        saveFile = new JMenuItem("Save (Alt + S)", KeyEvent.VK_S);
        saveFile.setFont(font);
        saveFile.addActionListener(this::actionPerformed);

        exitFile = new JMenuItem("Exit (Alt + E)", KeyEvent.VK_E);
        exitFile.setFont(font);
        exitFile.addActionListener(this::actionPerformed);

        fileMenu.add(newWindowFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(exitFile);

        aboutMenu = new JMenu("About", true); aboutMenu.setFont(font);
        aboutMenuItem = new JMenuItem("About (Alt + A)", KeyEvent.VK_A);
        aboutMenuItem.setFont(font);
        aboutMenuItem.addActionListener(this::actionPerformed);
        aboutMenu.add(aboutMenuItem);

        settingsMenu = new JMenu("Settings", true); settingsMenu.setFont(font);
        settingsMenuItem = new JMenuItem("Settings (Alt + S)", KeyEvent.VK_S);
        settingsMenuItem.setFont(font);
        settingsMenuItem.addActionListener(this::actionPerformed);
        settingsMenu.add(settingsMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(settingsMenu);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == newWindowFile) {
            FrameWindow.frame();
        } else if (actionEvent.getSource() == openFile) {
            fileChooser.setFileFilter(textFile);
            fileChooser.setFileFilter(pythonFile);
            fileChooser.setFileFilter(javaFile);
            fileChooser.setFileFilter(javaScriptFile);
            fileChooser.setFileFilter(htmlFile);
            fileChooser.setFileFilter(cssFile);
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileReader = new FileReader(file);
                    int data = fileReader.read();
                    while (data != -1) {
                        data = fileReader.read();
                        textArea.append(String.valueOf((char) data));

                    }
                } catch (IOException IOError) {
                    System.out.println(IOError.getLocalizedMessage());
                } finally {
                    try {
                        fileReader.close();
                    } catch (IOException ioException) {
                        System.out.println(ioException.getLocalizedMessage());
                    }
                }
            }
        } else if (actionEvent.getSource() == saveFile) {
            fileChooser.setFileFilter(textFile);
            fileChooser.setFileFilter(pythonFile);
            fileChooser.setFileFilter(javaFile);
            fileChooser.setFileFilter(javaScriptFile);
            fileChooser.setFileFilter(htmlFile);
            fileChooser.setFileFilter(cssFile);
            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileWriter = new FileWriter(file);
                    fileWriter.write(textArea.getText());
                } catch (IOException ioError) {
                    System.out.println(ioError.getLocalizedMessage());
                } finally {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (actionEvent.getSource() == exitFile) {
            System.exit(0);
        } else if (actionEvent.getSource() == aboutMenuItem) {
            new AboutSection();
        } else if (actionEvent.getSource() == settingsMenuItem) {
            //new SettingsWindow();

            comboBox = new JComboBox<>((fonts));
            comboBox.setFont(font);
            comboBox.setSelectedItem("Times new roman");
            comboBox.setEditable(false);
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textArea.setFont(new Font((String)comboBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
                }
            });

            spinner = new JSpinner();
            SpinnerModel spinnerModel = new SpinnerNumberModel(30, 1, 100, 1);
            spinner.setModel(spinnerModel);
            spinner.setFont(font);
            spinner.setFocusable(false);
            spinner.addChangeListener(this::stateChanged);

            fontColorButton = new JButton("Font Color");
            fontColorButton.setFont(font);
            fontColorButton.setFocusable(false);
            fontColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color fontColor = JColorChooser.showDialog(null, "Font Color", Color.BLACK);
                    textArea.setForeground(fontColor);
                }
            });

            backgroundColorButton = new JButton("Background Color");
            backgroundColorButton.setFocusable(false);
            backgroundColorButton.setFont(font);
            backgroundColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color backgroundColor = JColorChooser.showDialog(null, "Background Color", Color.WHITE);
                    textArea.setBackground(backgroundColor);
                }
            });

            JPanel fontPanel = new JPanel();
            fontPanel.add(comboBox);
            fontPanel.add(spinner);

            JPanel colorPanel = new JPanel();
            colorPanel.add(fontColorButton);
            colorPanel.add(backgroundColorButton);

            JPanel radioButtonPanel = new JPanel();
            ButtonGroup buttonGroup = new ButtonGroup();
            JRadioButton lightTheme = new JRadioButton("Light Theme", true);
            lightTheme.setFont(font);
            lightTheme.setFocusable(false);
            JRadioButton darkTheme = new JRadioButton("Dark Theme", false);
            darkTheme.setFocusable(false);
            darkTheme.setFont(font);
            buttonGroup.add(lightTheme); buttonGroup.add(darkTheme);
            radioButtonPanel.add(lightTheme); radioButtonPanel.add(darkTheme);

            lightTheme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textArea.setForeground(Color.BLACK);
                    textArea.setBackground(Color.WHITE);
                    textArea.setCaretColor(Color.BLACK);

                    scrollPane.setBackground(Color.WHITE);scrollPane.setForeground(Color.BLACK);

                    menuBar.setBackground(Color.WHITE);
                    fileMenu.setForeground(Color.BLACK); aboutMenu.setForeground(Color.BLACK); settingsMenu.setForeground(Color.BLACK);
                    newWindowFile.setForeground(Color.BLACK); openFile.setForeground(Color.BLACK); saveFile.setForeground(Color.BLACK); exitFile.setForeground(Color.BLACK);
                    newWindowFile.setBackground(Color.WHITE); openFile.setBackground(Color.WHITE); saveFile.setBackground(Color.WHITE); exitFile.setBackground(Color.WHITE);
                    aboutMenuItem.setForeground(Color.BLACK); aboutMenuItem.setBackground(Color.WHITE);
                    settingsMenuItem.setForeground(Color.BLACK); settingsMenuItem.setBackground(Color.WHITE);

                    dialogBox.getContentPane().setBackground(Color.WHITE);
                    fontPanel.setForeground(Color.BLACK); fontPanel.setBackground(Color.WHITE); fontPanel.setOpaque(true);
                    comboBox.setForeground(Color.BLACK); comboBox.setBackground(Color.WHITE);
                    spinner.setForeground(Color.BLACK); spinner.setBackground(Color.WHITE);
                    fontColorButton.setForeground(Color.BLACK); fontColorButton.setBackground(Color.WHITE);
                    colorPanel.setForeground(Color.BLACK); colorPanel.setBackground(Color.WHITE); colorPanel.setOpaque(true);
                    backgroundColorButton.setForeground(Color.BLACK); backgroundColorButton.setBackground(Color.WHITE);
                    radioButtonPanel.setForeground(Color.BLACK); radioButtonPanel.setBackground(Color.WHITE); radioButtonPanel.setOpaque(true);
                    lightTheme.setForeground(Color.BLACK); lightTheme.setBackground(Color.WHITE);
                    darkTheme.setForeground(Color.BLACK); darkTheme.setBackground(Color.WHITE);
                }
            });

            darkTheme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textArea.setForeground(Color.WHITE);
                    textArea.setBackground(Color.BLACK);
                    textArea.setCaretColor(Color.WHITE);

                    scrollPane.setBackground(Color.BLACK);scrollPane.setForeground(Color.WHITE);

                    menuBar.setBackground(Color.BLACK);
                    fileMenu.setForeground(Color.WHITE); aboutMenu.setForeground(Color.WHITE); settingsMenu.setForeground(Color.WHITE);
                    newWindowFile.setForeground(Color.WHITE); openFile.setForeground(Color.WHITE); saveFile.setForeground(Color.WHITE); exitFile.setForeground(Color.WHITE);
                    newWindowFile.setBackground(Color.BLACK); openFile.setBackground(Color.BLACK); saveFile.setBackground(Color.BLACK); exitFile.setBackground(Color.BLACK);
                    aboutMenuItem.setForeground(Color.WHITE); aboutMenuItem.setBackground(Color.BLACK);
                    settingsMenuItem.setForeground(Color.WHITE); settingsMenuItem.setBackground(Color.BLACK);

                    dialogBox.getContentPane().setBackground(Color.BLACK);
                    fontPanel.setForeground(Color.WHITE); fontPanel.setBackground(Color.BLACK); fontPanel.setOpaque(true);
                    comboBox.setForeground(Color.WHITE); comboBox.setBackground(Color.BLACK);
                    spinner.setForeground(Color.WHITE); spinner.setBackground(Color.BLACK);
                    fontColorButton.setForeground(Color.WHITE); fontColorButton.setBackground(Color.BLACK);
                    colorPanel.setForeground(Color.WHITE); colorPanel.setBackground(Color.BLACK); colorPanel.setOpaque(true);
                    backgroundColorButton.setForeground(Color.WHITE); backgroundColorButton.setBackground(Color.BLACK);
                    radioButtonPanel.setForeground(Color.WHITE); radioButtonPanel.setBackground(Color.BLACK); radioButtonPanel.setOpaque(true);
                    lightTheme.setForeground(Color.WHITE); lightTheme.setBackground(Color.BLACK);
                    darkTheme.setForeground(Color.WHITE); darkTheme.setBackground(Color.BLACK);
                }
            });

            dialogBox = new JDialog();
            dialogBox.setTitle("Settings");
            dialogBox.setSize(450, 200);
            dialogBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialogBox.setLocationRelativeTo(null);
            dialogBox.setLayout(new FlowLayout());
            dialogBox.setResizable(true);
            dialogBox.add(fontPanel);
            dialogBox.add(colorPanel);
            dialogBox.add(radioButtonPanel);
            dialogBox.setVisible(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        if (changeEvent.getSource() == spinner) {
            textArea.setFont(new Font(textArea.getFont().getFontName(), Font.PLAIN, (int) spinner.getValue()));
        }
    }
}


