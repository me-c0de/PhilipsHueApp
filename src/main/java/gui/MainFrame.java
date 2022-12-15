package gui;

import api.Controller;

import java.io.IOException;
import javax.swing.*;
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
    private JButton refreshLightsButton;
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
                    controller.setLightsOff();
                    offOnBtn.setText("An");
                    offOnIsOn = !offOnIsOn;
                } else {
                    controller.setLightsOn();
                    offOnBtn.setText("Aus");
                    offOnIsOn = !offOnIsOn;
                }
            }
        });
        brightnessChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeBrightness(brightness.getValue());
            }
        });
        saturationChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeSaturation(saturationSlider.getValue());
            }
        });
        weihnachtsmodusAnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (weihnachtsmodusIsOn) {
                    weihnachtsmodusAnButton.setText("Jingle Bells an");
                    weihnachtsmodusIsOn = false;
                    controller.setChristmasMode(false);
                } else {
                    weihnachtsmodusAnButton.setText("Jingle Bells aus");
                    weihnachtsmodusIsOn = true;
                    controller.setChristmasMode(true);
                }
            }
        });
        refreshLightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.refresh();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
