package com.crayonbox.orisa;

/**
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public class Settings {
    public final static Settings DEFAULT_SETTINGS = new Settings("");

    private String token;

    public Settings(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void getToken(String token) {
        this.token = token;
    }
}