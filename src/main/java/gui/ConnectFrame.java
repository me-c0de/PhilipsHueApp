package gui;

import api.Controller;

import javax.swing.*;
import java.io.IOException;

public class ConnectFrame extends JPanel {
    private JButton connect;
    private JPanel mainPanel;

    public ConnectFrame(DesktopView desktopView) {

        setVisible(true);
        setSize(720, 550);
        add(mainPanel);

        connect.addActionListener(e -> {
            try {
                Controller controller = new Controller();
                desktopView.setContentPane(new MainFrame(controller));
            } catch (IOException | InterruptedException ex) {

                JOptionPane.showMessageDialog(null, "Error");
            }


        });
    }
}
