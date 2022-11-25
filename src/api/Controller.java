package api;

import gui.ConnectFrame;
import gui.DesktopView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Controller {
    private String apiAddress;

    public Controller(String apiAddress, DesktopView desktopView) {
        this.apiAddress = "http://localhost:8000/api/";
        HttpURLConnection con;

        try {
            URL url = new URL(this.apiAddress);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write("{\"devicetype\":\"my_hue_app#iphone peter\"}");
            osw.flush();
            osw.close();
            System.out.println(String.valueOf(con.getResponseCode()));
            readResponse(con);

        } catch (ProtocolException | MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String readResponse(HttpURLConnection con) throws IOException {
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
        return content.toString();
    }
}
