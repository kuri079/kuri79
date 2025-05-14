package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_LOCATION = 1;
    private LocationManager manager;
    private TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationTextView = findViewById(R.id.locationTextView);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            startLocationUpdates();
        }
    }

    // onResume で位置情報の更新をリクエスト
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000, 10, this); // 1000msごとまたは10mごとの更新
        }
    }

    // onPause で位置情報の更新を停止
    @Override
    protected void onPause() {
        super.onPause();
        if (manager != null) {
            manager.removeUpdates(this); // リスナーを解除
        }
    }

    private void startLocationUpdates() {
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                manager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 1000, 10, this); // 1000msごとまたは10mごとの更新
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // 位置情報の取得に成功したときの処理
        double lat = location.getLatitude();  // 緯度
        double lng = location.getLongitude(); // 経度
        // 位置情報をトーストメッセージで表示
        Toast.makeText(this, String.format("%.3f %.3f", lat, lng), Toast.LENGTH_SHORT).show();
        // TextViewにも表示する（オプション）
        locationTextView.setText("緯度: " + lat + "\n経度: " + lng);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}
}
