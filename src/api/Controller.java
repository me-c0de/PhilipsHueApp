package api;

import gui.DesktopView;

import java.awt.*;
import java.io.IOException;

public class Controller {
  private String apiAddress;

  private RestAPI restAPI;

  private ChristmasThread christmasThread;

  public int numberOfLights(){
    return this.restAPI.numberOfLights;
  }


  public Controller(String apiAddress, DesktopView desktopView) throws IOException, InterruptedException {
    this.apiAddress = "http://localhost:8000/api/";
    this.restAPI = new RestAPI(apiAddress);
  }

  public void refreshLights(){
    try {
      this.restAPI.initLights();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void setAllLightsOff() {
    for (int i = 1; i <= restAPI.numberOfLights; i++) {
      restAPI.setLightStatus(i, false);
    }
  }

  public void setAllLights(boolean on) {
    if (on) {
      setAllLightsOn();
    } else {
      setAllLightsOff();
    }
  }

  public void setAllLightsOn() {
    for (int i = 1; i <= restAPI.numberOfLights; i++) {
      restAPI.setLightStatus(i, true);
    }
  }

  public void changeAllBrightness(int value) {
    for (int i = 1; i <= restAPI.numberOfLights; i++) {
      restAPI.setLightBrightness(i, value);
    }
  }

  public void changeColor(Color color, int index) {
    restAPI.setLightColor(index, color);
  }

  public void changeAllColor(Color color) {
    for (int i = 1; i <= restAPI.numberOfLights; i++) {
      restAPI.setLightColor(i, color);
    }
  }

  public void changeAllSaturation(int sat) {
    for (int i = 1; i <= restAPI.numberOfLights; i++) {
      restAPI.setLightSaturation(i, sat);
    }
  }


  public void setChristmasMode(boolean setOn) {
    if (setOn) {
      if (christmasThread == null) {
        this.christmasThread = new ChristmasThread(this);
      }

      if (christmasThread.isAlive()) {
        christmasThread.interrupt();
      } else {
        christmasThread.start();
      }

    } else {
      if (christmasThread.isAlive()) {
        christmasThread.interrupt();
        try {
          christmasThread.join();
          this.christmasThread = null;
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

    }

  }
}
