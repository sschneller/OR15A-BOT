package com.crayonbox.orisa;

import com.crayonbox.orisa.GUI.FirstTime;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Bot {

    private static HashMap<String, Settings> settings = new HashMap<>();

    public static void main(String[] args) throws LoginException, InterruptedException, RateLimitedException {

        if(new File("settings.json").exists()) {
            try {
                JSONObject loadedSettings = new JSONObject(new String(Files.readAllBytes(Paths.get("settings.json"))));
                settings.put("token", new Settings(loadedSettings.has("token") ? loadedSettings.getString("token") : null));
//                loadedSettings.keySet().forEach((id) -> {
////                    JSONObject o = loadedSettings.getJSONObject(id);
//
//
//                    settings.put(id, new Settings(o.has("token") ? o.getString("token") : null));
//                });

            }
            catch(IOException | JSONException e) {
                e.printStackTrace();
            }
            new OrisaListener(settings.get("token").getToken());
        }
        else {
            FirstTime ft = new FirstTime(new Bot());
            ft.setVisible(true);
        }
    }

    public void nextStep(String token) {
        try {
            new OrisaListener(token);
        }
        catch(LoginException | InterruptedException | RateLimitedException e) {
            e.printStackTrace();
        }
    }
}
