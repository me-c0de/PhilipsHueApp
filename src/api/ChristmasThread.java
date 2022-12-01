package api;

import java.awt.*;

public class ChristmasThread extends Thread {
  private Controller controller;
  private boolean isOn = false;

  private Color green = Color.GREEN;
  private Color red = Color.RED;

  public ChristmasThread(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void run() {
    // set All light on
    controller.setAllLights(true);

    while (!isInterrupted()) {

      // Switch light colors red -> green ; green -> red
      for (int i = 1; i <= controller.numberOfLights(); i++) {
        if (i % 2 == 0) {
          this.controller.changeColor(isOn ? green : red, i);
        } else {
          this.controller.changeColor(isOn ? red : green, i);
        }
      }


      isOn = !isOn;

      try {
        sleep((60 * 1000 / 104)); // 104 == bpm Jingle Bells
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}
