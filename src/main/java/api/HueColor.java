package api;

import java.awt.*;

public class HueColor {

    public static int getColor(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        float[] floats = Color.RGBtoHSB(red, green, blue, null);
        floats[0] *= 65535;
        return Math.round(floats[0]);
    }
}
