package io.github.dcheong.yelptest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WheelActivity extends AppCompatActivity {

    private int counter;
    private Restaurant[] restaurants;
    @Bind(R.id.restName)
    TextView restaurantName;
    @Bind(R.id.restImage)
    ImageView restaurantImage;
    @Bind(R.id.next)
    Button next;
    @Bind(R.id.back)
    Button back;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        restaurants = MainActivity.restaurants;
        ButterKnife.bind(this);
        counter = 0;
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextRestaurant();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backRestaurant();
            }
        });
    }
    public void initRestaurantView() {
        restaurantImage.setImageBitmap(loadImageFromURL(restaurants[counter].getImageURL()));
        restaurantName.setText(restaurants[counter].getName());
    }
    public void nextRestaurant() {
        if(counter<restaurants.length-1) {
            counter++;
            restaurantImage.setImageBitmap(loadImageFromURL(restaurants[counter].getImageURL()));
            restaurantName.setText(restaurants[counter].getName());
        }

    }

    public void backRestaurant() {
        if(counter>0) {
            counter--;
            restaurantImage.setImageBitmap(loadImageFromURL(restaurants[counter].getImageURL()));
            restaurantName.setText(restaurants[counter].getName());
        }
    }

    public Bitmap loadImageFromURL(URL myFileUrl){
        try {

            HttpURLConnection conn =
                    (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            return BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
