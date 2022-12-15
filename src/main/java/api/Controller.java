package api;

import api.model.Lights;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Controller {

  private ChristmasThread christmasThread;

  private String api = "http://localhost:8000/api/";

  private final String username = "newdeveloper";

  private Lights lights;
  private HttpClient httpClient = HttpClient.newHttpClient();

  public int numberOfLights;

  public int lightCount(){
    return this.numberOfLights;
  }


  public Controller() throws IOException, InterruptedException {

    HttpRequest httpRequest1 = HttpRequest.newBuilder()
        .uri(URI.create(api + username + "/lights"))
        .GET()
        .build();
    HttpResponse<String> getResponse = null;
    getResponse = httpClient.send(httpRequest1, HttpResponse.BodyHandlers.ofString());

    this.lights = new Lights(getResponse.body());
    this.numberOfLights = this.lights.size();

    HttpRequest httpRequest2 = HttpRequest.newBuilder()
        .uri(URI.create(api + username + "/lights/" + 1 + "/state"))
        .PUT(HttpRequest.BodyPublishers.ofString("{\"on\":" + true + "}"))
        .build();

    try {
      httpClient.send(httpRequest2, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void refresh() throws IOException, InterruptedException {
    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(api + username + "/lights"))
        .GET()
        .build();
    HttpResponse<String> getResponse = null;
    getResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

    this.lights = new Lights(getResponse.body());
    this.numberOfLights = this.lights.size();
  }

  public void setLightsOff() {
    for (int i = 1; i <= numberOfLights; i++) {
      HttpRequest httpRequest = HttpRequest.newBuilder()
          .uri(URI.create(api + username + "/lights/" + i + "/state"))
          .PUT(HttpRequest.BodyPublishers.ofString("{\"on\":" + false + "}"))
          .build();

      try {
        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void setLights(boolean on) {
    if (on) {
      setLightsOn();
    } else {
      setLightsOff();
    }
  }

  public void setLightsOn() {
    for (int i = 1; i <= numberOfLights; i++) {
      HttpRequest httpRequest = HttpRequest.newBuilder()
          .uri(URI.create(api + username + "/lights/" + i + "/state"))
          .PUT(HttpRequest.BodyPublishers.ofString("{\"on\":" + true + "}"))
          .build();

      try {
        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void changeBrightness(int value) {
    for (int i = 1; i <= numberOfLights; i++) {
      HttpRequest httpRequest = HttpRequest.newBuilder()
          .uri(URI.create(api + username + "/lights/" + i + "/state"))
          .PUT(HttpRequest.BodyPublishers.ofString("{\"bri\":" + value + "}"))
          .build();

      try {
        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void modifyColor(Color color, int index) {
    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(api + username + "/lights/" + index + "/state"))
        .PUT(HttpRequest.BodyPublishers.ofString("{\"hue\":" + HueColor.getColor(color) + "}"))
        .build();

    try {
      httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void changeAllColor(Color color) {
    for (int i = 1; i <= numberOfLights; i++) {
      this.modifyColor(color, i);
    }
  }

  public void changeSaturation(int sat) {
    for (int i = 1; i <= numberOfLights; i++) {
      HttpRequest httpRequest = HttpRequest.newBuilder()
          .uri(URI.create(api + username + "/lights/" + i + "/state"))
          .PUT(HttpRequest.BodyPublishers.ofString("{\"sat\":" + sat + "}"))
          .build();

      try {
        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
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
