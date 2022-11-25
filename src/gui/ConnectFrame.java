package gui;

import javax.swing.*;

public class ConnectFrame extends JPanel {
    private JTextField textField1;
    private JButton connectButton;
    private JPanel mainPanel;

    public ConnectFrame(DesktopView desktopView) {
        setVisible(true);
        setSize(450, 150);
        add(mainPanel);
        /*setContentPane(mainPanel);
        setTitle("Phillips Hue Demo");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);*/
        connectButton.addActionListener(e -> {
            String address = textField1.getText();
            desktopView.setContentPane(new MainFrame());
            System.out.println(address);
        });
    }
}
