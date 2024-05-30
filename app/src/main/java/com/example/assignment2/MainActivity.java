/*
Assignment 2
Assignment2.zip
Chinedu Ibeanu
 */
package com.example.assignment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static int redProgress;
    public static int greenProgress;
    public static int blueProgress;
    public static String rgbValue;
    ImageView box;
    TextView redText;
    TextView greenText;
    TextView blueText;

    TextView hexText;

    TextView rgbText;

    SeekBar redBar;
    SeekBar greenBar;
    SeekBar blueBar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        box = findViewById(R.id.box);

        //Create references to the color Texts in our UI
        redText = findViewById(R.id.redText);
        greenText = findViewById(R.id.greenText);
        blueText = findViewById(R.id.blueText);


        //Creating references to the color bars in our UI
        redBar = findViewById(R.id.redBar);
        greenBar = findViewById(R.id.greenBar);
        blueBar = findViewById(R.id.blueBar);
        rgbText = findViewById(R.id.rgbText);
        hexText = findViewById(R.id.hexText);

        //Setting the references equal to the default value of the progress set in our UI
        redText.setText(String.valueOf(redBar.getProgress()));
        greenText.setText(String.valueOf(greenBar.getProgress()));
        blueText.setText(String.valueOf(blueBar.getProgress()));

        //Quick variables just to easily track progress as integer
        redProgress = redBar.getProgress();
        greenProgress = greenBar.getProgress();
        blueProgress = blueBar.getProgress();



        //Calling our hexConverter function and setting the return value as the text for hexText.
        hexText.setText(hexConverter(redProgress, greenProgress, blueProgress));

        rgbValue = String.format("(%d, %d, %d)", redProgress, greenProgress, blueProgress);
        rgbText.setText(rgbValue);

        //Here is where we translate all those default values set in the UI to actual color in our box
        box.setColorFilter(Color.argb(255, redBar.getProgress(), greenBar.getProgress(), blueBar.getProgress()));

        //Creating listeners for our Red seek bar
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //updating the value of the text next to the redbar.
                redText.setText(String.valueOf(progress));
                //Setting the color of the box to whatever change has been made from our slider.
                //We do this in the onProgressChanged instead of the onStopTrackingTouch to make our color change in real time rather than the end of our slider action.
                box.setColorFilter(Color.argb(255, progress, greenBar.getProgress(), blueBar.getProgress()));
                hexText.setText(hexConverter(progress, greenBar.getProgress(), blueBar.getProgress()));
                redProgress = progress;
                greenProgress = greenBar.getProgress();
                blueProgress = blueBar.getProgress();
                rgbValue = String.format("(%d, %d, %d)", redProgress, greenProgress, blueProgress);
                rgbText.setText(rgbValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenText.setText(String.valueOf(progress));
                box.setColorFilter(Color.argb(255, redBar.getProgress(), progress, blueBar.getProgress()));
                hexText.setText(hexConverter(redBar.getProgress(), progress, blueBar.getProgress()));
                redProgress = redBar.getProgress();
                greenProgress = progress;
                blueProgress = blueBar.getProgress();
                rgbValue = String.format("(%d, %d, %d)", redProgress, greenProgress, blueProgress);
                rgbText.setText(rgbValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueText.setText(String.valueOf(progress));
                box.setColorFilter(Color.argb(255, redBar.getProgress(), greenBar.getProgress(), progress));
                hexText.setText(hexConverter(redBar.getProgress(), greenBar.getProgress(), progress));
                redProgress = redBar.getProgress();
                greenProgress = greenBar.getProgress();
                blueProgress = progress;
                rgbValue = String.format("(%d, %d, %d)", redProgress, greenProgress, blueProgress);
                rgbText.setText(rgbValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Initializing listener for buttons
        findViewById(R.id.whiteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redBar.setProgress(255);
                redText.setText(String.valueOf(redBar.getProgress()));
                greenBar.setProgress(255);
                greenText.setText(String.valueOf(greenBar.getProgress()));
                blueBar.setProgress(255);
                blueText.setText(String.valueOf(blueBar.getProgress()));
            }
        });

        findViewById(R.id.blackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redBar.setProgress(0);
                redText.setText(String.valueOf(redBar.getProgress()));
                greenBar.setProgress(0);
                greenText.setText(String.valueOf(greenBar.getProgress()));
                blueBar.setProgress(0);
                blueText.setText(String.valueOf(blueBar.getProgress()));
            }
        });

        findViewById(R.id.blueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redBar.setProgress(0);
                redText.setText(String.valueOf(redBar.getProgress()));
                greenBar.setProgress(0);
                greenText.setText(String.valueOf(greenBar.getProgress()));
                blueBar.setProgress(255);
                blueText.setText(String.valueOf(blueBar.getProgress()));
            }
        });

        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redBar.setProgress(64);
                redText.setText(String.valueOf(redBar.getProgress()));
                greenBar.setProgress(128);
                greenText.setText(String.valueOf(greenBar.getProgress()));
                blueBar.setProgress(0);
                blueText.setText(String.valueOf(blueBar.getProgress()));
            }
        });
    }

    //Method to convert rgb values into a hexvalue
    public static String hexConverter(int red, int green, int blue){

        return String.format("#%02X%02X%02X", red, green, blue);
    }

}