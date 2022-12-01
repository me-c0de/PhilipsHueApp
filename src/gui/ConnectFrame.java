package gui;

import api.Controller;

import javax.swing.*;
import java.io.IOException;

public class ConnectFrame extends JPanel {
    private JTextField textField1;
    private JButton connectButton;
    private JPanel mainPanel;

    public ConnectFrame(DesktopView desktopView) {
        setVisible(true);
        setSize(600, 450);
        add(mainPanel);
        /*setContentPane(mainPanel);
        setTitle("Phillips Hue Demo");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);*/
        connectButton.addActionListener(e -> {
            String address = textField1.getText();
            //System.out.println(address);
            try {
                Controller controller = new Controller(address, desktopView);
                desktopView.setContentPane(new MainFrame(controller));
            } catch (IOException | InterruptedException ex) {

                JOptionPane.showMessageDialog(null, "Error");
                //throw new RuntimeException(ex);
            }


        });
    }
}
