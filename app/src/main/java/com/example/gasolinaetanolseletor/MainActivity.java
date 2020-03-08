package com.example.gasolinaetanolseletor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private TextView valueGasTextView;
    private TextView valueEthanolTextView;

    private ImageView gasImageView;
    private ImageView ethanolImageView;

    private TextInputEditText betterFuelTextInputEditText;

    private double gasValue = 4.0;
    private double ethanolValue = 3.0;
    private String betterFuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueGasTextView = findViewById(R.id.valueGasTextView);
        valueEthanolTextView = findViewById(R.id.valueEthanolTextView);

        gasImageView = findViewById(R.id.gasImageView);
        ethanolImageView = findViewById(R.id.ethanolImageView);

        betterFuelTextInputEditText = findViewById(R.id.betterFuelTextInputEditText);

        zerarTudo();

        SeekBar percentGasSeekBar = findViewById(R.id.percentGasSeekBar);
        SeekBar percentEthanolSeekBar = findViewById(R.id.percentEthanolSeekBar);

        percentGasSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gasValue = progress / 10D;
                valueGasTextView.setText(currencyFormat.format(gasValue));
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        percentEthanolSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ethanolValue = progress / 10D;
                valueEthanolTextView.setText(currencyFormat.format(ethanolValue));
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void calcular(){
        double coeficiente =  this.ethanolValue /this.gasValue;

        if(coeficiente >= 0.7D){
            gasImageView.setVisibility(View.VISIBLE);
            ethanolImageView.setVisibility(View.INVISIBLE);

            this.betterFuel = getString(R.string.gasoline);

        }
        else {
            gasImageView.setVisibility(View.INVISIBLE);
            ethanolImageView.setVisibility(View.VISIBLE);

            this.betterFuel = getString(R.string.ethanol);
        }

        betterFuelTextInputEditText.setText(betterFuel);
    }

    private void zerarTudo(){

        this.betterFuel = getString(R.string.gasoline);

        valueGasTextView.setText(currencyFormat.format(gasValue));
        valueEthanolTextView.setText(currencyFormat.format(ethanolValue));

        gasImageView.setVisibility(View.VISIBLE);
        ethanolImageView.setVisibility(View.INVISIBLE);
    }
}
