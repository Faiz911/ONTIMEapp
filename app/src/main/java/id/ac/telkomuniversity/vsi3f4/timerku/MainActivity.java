package id.ac.telkomuniversity.vsi3f4.timerku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;



public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long PauseOffSet = 0;
    private boolean isPlaying = false;
    private ToggleButton toggleButton;
    private Button reset_btn;
    private Button riwayat;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometer);
        toggleButton = findViewById(R.id.Toggle);
        reset_btn = findViewById(R.id.reset_btn);
        riwayat = findViewById(R.id.riwayat);

        toggleButton.setText(null);
        toggleButton.setTextOff(null);
        toggleButton.setTextOn(null);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //kodingan stopwatch

                if(b){
                    chronometer.setBase(SystemClock.elapsedRealtime()- PauseOffSet);
                    chronometer.start();
                    isPlaying = true;

                } else {
                    chronometer.stop();
                    PauseOffSet = SystemClock.elapsedRealtime()- chronometer.getBase();
                    isPlaying = false;
                }
            }
        });

        //membuat kodingan reset stopwatch
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                PauseOffSet = 0;
                if(isPlaying){
                    isPlaying = true;
                } else {
                    isPlaying = false;
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setMessage("Yakin ingin keluar?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(MainActivity.this, "Anda keluar", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "Lanjutkan aktivitasmu!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


}