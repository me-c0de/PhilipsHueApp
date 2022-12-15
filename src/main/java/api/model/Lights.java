package api.model;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lights extends ArrayList {
    public Lights(String body) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(body);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        int i = 1;
        while (jsonObject.has(String.valueOf(i))) {
            try {
                add(gson.fromJson(jsonObject.get(String.valueOf(i)).toString(), SingleLight.class));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            i++;
        }

    }
}
