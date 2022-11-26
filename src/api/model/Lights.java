package api.model;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lights extends ArrayList {
    public Lights(String body) {
        JSONObject jsonObject = new JSONObject(body);
        Gson gson = new Gson();
        int i = 1;
        while (jsonObject.has(String.valueOf(i))) {
            add(gson.fromJson(jsonObject.get(String.valueOf(i)).toString(), SingleLight.class));
            i++;
        }

    }
}
