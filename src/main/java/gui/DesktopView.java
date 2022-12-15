package gui;

import javax.swing.*;

public class DesktopView extends JFrame {


    public DesktopView() {
        setTitle("Philips Hue Desktop");
        setSize(600, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        AddFrame panel = new AddFrame(this);
        setContentPane(panel);
    }

    public void ChangePane(JPanel panel) {
        setContentPane(panel);
    }
}
