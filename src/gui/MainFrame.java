package gui;

import api.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JPanel {
    private JPanel mainPanel;
    private JButton offOnBtn;
    private boolean offOnIsOn = true;
    private JSlider brightness;
    private JButton brightnessChangeButton;
    private JButton changeColorButton;
    private JSlider saturationSlider;
    private JButton saturationChangeButton;
    private JButton weihnachtsmodusAnButton;
    private boolean weihnachtsmodusIsOn = false;

    private Controller controller;

    public MainFrame(Controller controller) {
        setVisible(true);
        setSize(600, 450);
        add(mainPanel);
        this.controller = controller;

        offOnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (offOnIsOn) {
                    controller.setAllLightsOff();
                    offOnBtn.setText("On");
                    offOnIsOn = !offOnIsOn;
                } else {
                    controller.setAllLightsOn();
                    offOnBtn.setText("Off");
                    offOnIsOn = !offOnIsOn;
                }
            }
        });
        brightnessChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeAllBrightness(brightness.getValue());
            }
        });
        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorChooser = new JColorChooser();
                Color color = JColorChooser.showDialog(null, "Pick a color", Color.white);
                controller.changeAllColor(color);
            }
        });
        saturationChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeAllSaturation(saturationSlider.getValue());
            }
        });
        weihnachtsmodusAnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
                if (weihnachtsmodusIsOn) {
                    weihnachtsmodusAnButton.setText("Weihnachtsmodus an");
                    weihnachtsmodusIsOn = false;
                } else {
                    weihnachtsmodusAnButton.setText("Weihnachtsmodus aus");
                    weihnachtsmodusIsOn = true;
                }
            }
        });
    }
}
