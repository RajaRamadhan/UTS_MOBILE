package uts.rajaramadhan.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Menampilkan splash screen selama 2 detik
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Setelah splash screen, arahkan ke LoginActivity
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Menutup splash screen agar tidak bisa kembali
            }
        }, 2000); // 2000ms = 2 detik
    }
}
