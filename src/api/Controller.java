package api;

import gui.DesktopView;

import java.awt.*;

public class Controller {
    private String apiAddress;

    private RestAPI restAPI;

    public Controller(String apiAddress, DesktopView desktopView) {
        this.apiAddress = "http://localhost:8000/api/";
        this.restAPI = new RestAPI(apiAddress);

    }

    public void setAllLightsOff() {
        for (int i = 1; i <= restAPI.numberOfLights; i++){
            restAPI.setLightStatus(i,false);
        }
    }

    public void setAllLightsOn() {
        for (int i = 1; i <= restAPI.numberOfLights; i++){
            restAPI.setLightStatus(i,true);
        }
    }

    public void changeAllBrightness(int value) {
        for (int i = 1; i <= restAPI.numberOfLights; i++){
            restAPI.setLightBrightness(i,value);
        }
    }

    public void changeAllColor(Color color) {
        for (int i = 1; i <= restAPI.numberOfLights; i++){
            restAPI.setLightColor(i,color);
        }
    }
    public void changeAllSaturation(int sat) {
        for (int i = 1; i <= restAPI.numberOfLights; i++){
            restAPI.setLightSaturation(i,sat);
        }
    }
}
