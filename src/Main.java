import api.HueColor;
import gui.DesktopView;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");
        new DesktopView();

        //HueColor.getHue(Color.BLACK);
        //HueColor.getHue(Color.GREEN); // 25500
        //HueColor.getHue(Color.BLUE); // 46920
        //HueColor.getHue(Color.RED); // 0 | 65535
    }
}
