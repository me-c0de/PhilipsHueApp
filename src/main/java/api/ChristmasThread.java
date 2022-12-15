package api;

import java.awt.*;

public class ChristmasThread extends Thread {
  private Controller controller;
  private boolean isOff = true;

  public ChristmasThread(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void run() {

    controller.setLights(true);

    while (!isInterrupted()) {

      for (int i = 1; i <= controller.lightCount(); i++) {
        if (i % 2 == 0) {
          this.controller.modifyColor(isOff ?  Color.GREEN : Color.RED, i);
        } else {
          this.controller.modifyColor(isOff ? Color.RED :  Color.GREEN, i);
        }
      }
      isOff = !isOff;

      try {
        sleep(((60 * 1000) / 208));
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}
