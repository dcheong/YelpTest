package io.github.dcheong.yelptest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.beust.jcommander.JCommander;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    private String loc = null;
    public static Restaurant[] restaurants = new Restaurant[20];
    private int counter = 0;
    @Bind(R.id.testButton)
    Button tButton;
    @Bind(R.id.textLocation)
    EditText searchLocation;
    @Bind(R.id.restaurantImage)
    ImageView restaurantImage;
    @Bind(R.id.restaurantName)
    TextView restaurantName;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create an instance of GoogleAPIClient.
        final GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

        ButterKnife.bind(this);
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YelpAPI yelpAPI = new YelpAPI();
                findLocation(mGoogleApiClient);
                try {
                    createRestaurants(yelpAPI.queryAPI(searchLocation.getText().toString(), loc));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                startWheel();

            }
        });
    }
    public void startWheel() {
        Intent intent = new Intent(MainActivity.this, WheelActivity.class);
        startActivity(intent);
    }


    public void findLocation(GoogleApiClient mGoogleApiClient2) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if(mGoogleApiClient2.isConnected()) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient2);
            if (mLastLocation != null) {
                loc = String.valueOf(mLastLocation.getLatitude()) + "," + String.valueOf(mLastLocation.getLongitude());
                System.out.println("Last location is " + loc);
            } else {
                loc = null;
                System.out.println("null location");
            }
        }
    }


    public void createRestaurants(JSONArray jsonArray) throws MalformedURLException {
        String output = new String();
        for(int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            Restaurant restaurant = new Restaurant(object);
            restaurants[i] = restaurant;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        System.out.println("connection failed");
    }

    @Override
    public void onConnected(Bundle bundle) {
        System.out.println("connected!");

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
