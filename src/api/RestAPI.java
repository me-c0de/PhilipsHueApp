package api;

import api.model.Lights;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestAPI {
    private String baseAPI = "http://localhost:8000/api/";

    private final String userName = "newdeveloper";

    private Lights lights;
    private HttpClient httpClient = HttpClient.newHttpClient();

    public int numberOfLights;

    public RestAPI(String baseAPI) throws IOException, InterruptedException {
        this.baseAPI = baseAPI;
        initLights();
        setLightStatus(1, true);

    }

    public void initLights() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseAPI + userName + "/lights"))
                //.POST(HttpRequest.BodyPublishers.ofString())
                .GET()
                .build();
        // System.out.println(httpRequest.uri().toString());
        HttpResponse<String> getResponse = null;
        getResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //System.out.println(getResponse.statusCode());
        //System.out.println(getResponse.body());

        this.lights = new Lights(getResponse.body());
        this.numberOfLights = this.lights.size();
    }


    public void setLightStatus(int index, boolean isOn) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseAPI + userName + "/lights/" + index + "/state"))
                .PUT(HttpRequest.BodyPublishers.ofString("{\"on\":" + isOn + "}"))
                .build();

        try {
            HttpResponse<String> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            //System.out.println(res.statusCode());
            //System.out.println(res.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setLightBrightness(int index, int value) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseAPI + userName + "/lights/" + index + "/state"))
                .PUT(HttpRequest.BodyPublishers.ofString("{\"bri\":" + value + "}"))
                .build();

        try {
            HttpResponse<String> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            //System.out.println(res.statusCode());
            //System.out.println(res.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLightColor(int index, Color color) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseAPI + userName + "/lights/" + index + "/state"))
                .PUT(HttpRequest.BodyPublishers.ofString("{\"hue\":" + HueColor.getHue(color) + "}"))
                .build();

        try {
            HttpResponse<String> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            //System.out.println(res.statusCode());
            //System.out.println(res.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void setLightSaturation(int index, int sat){
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseAPI + userName + "/lights/" + index + "/state"))
                .PUT(HttpRequest.BodyPublishers.ofString("{\"sat\":" + sat + "}"))
                .build();

        try {
            HttpResponse<String> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            //System.out.println(res.statusCode());
            //System.out.println(res.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
