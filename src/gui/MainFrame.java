package gui;

import api.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JPanel {
    private JPanel mainPanel;
    private JButton changeLightButton;

    public MainFrame(Controller controller) {
        setVisible(true);
        setSize(450, 150);
        add(mainPanel);
        /*setContentPane(mainPanel);
        setTitle("Phillips Hue Demo");
        setSize(450, 150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);*/

        changeLightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
