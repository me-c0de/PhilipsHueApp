import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//This PhilipsTestApp checks to see if Light 1 is on, and if it is, then it switches it off.
public class PhilipsTestApp {
    static int checkInterval = 5000; //i.e. 5 seconds
    static int leaveOnInterval = 1000; //i.e. 1 second.
    static int leaveOffInterval = 1000; //i.e. 1 second.
    static String hueLightIPAddress = "http://localhost:8000/api/newdeveloper/lights/1/";

    public static void main(String[] args) {
        System.out.println("\nApplication Started with checkInterval == " + checkInterval/1000 + " seconds and leaveOnInterval == " + leaveOnInterval/1000 + " seconds.");
        while (true) {
            try {
                Thread.sleep(checkInterval);
                if (checkIfHueIsOn()) {
                    Thread.sleep(leaveOnInterval);
                    switchHueLightOnOrOff("{\"on\":false}"); //This is the Hue-specific payload.
                } else {
                    Thread.sleep(leaveOffInterval);
                    switchHueLightOnOrOff("{\"on\":true}");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkIfHueIsOn() {
        URL url;
        HttpURLConnection con;
        String hueIsOn =  "\"on\":true";

        try {
            url = new URL(hueLightIPAddress);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            //System.out.println("status: " + status);
            if (status > 299) {
                System.out.println(con.getErrorStream());
            } else {
                //System.out.println(con.getInputStream());
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();
                //System.out.println(content);

                //Check if the Hue light is ON.
                if (content.toString().contains(hueIsOn)) {
                    System.out.println("Light is on. Switching off in " + leaveOnInterval/1000 + " seconds.");
                    return(true);
                }
            }
            return(false);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return(false);
        } catch (IOException e) {
            e.printStackTrace();
            return(false);
        }
    } //checkIfHueIsOn

    public static void switchHueLightOnOrOff (String payload) {
        URL url;
        HttpURLConnection con;

        try {
            url = new URL(hueLightIPAddress + "state");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(payload);
            osw.flush();
            osw.close();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            if (status > 299) {
                System.out.println(con.getErrorStream());
            } else {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();
                System.out.println(content);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //switchHueLightOff
}
