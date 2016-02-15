package io.github.dcheong.yelptest;

import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Douglas on 2/14/2016.
 */
public class Restaurant {
    private String name;
    private URL imageURL;
    public Restaurant(JSONObject jsonObject) throws MalformedURLException {
        name = jsonObject.get("name").toString();
        String stringURL = jsonObject.get("image_url").toString();
        stringURL = stringURL.replace("ms.jpg","348s.jpg");
        imageURL = new URL(stringURL);
    }
    public URL getImageURL() {
        return imageURL;
    }
    public String getName() {
        return name;
    }
}
