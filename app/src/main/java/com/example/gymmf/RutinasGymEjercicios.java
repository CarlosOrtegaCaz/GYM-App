package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RutinasGymEjercicios extends AppCompatActivity {

    CardView Ejer1, Ejer2, Ejer3, Ejer4, Ejer5;
    Button Regresar;
    Intent intentIns;
    ImageView Header;
    TextView Titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rutinas_gym_ejercicios);

        intentIns = getIntent();

        Titulo = findViewById(R.id.textView35);
        Header = findViewById(R.id.imageView21);
        switch (intentIns.getIntExtra("Dia", 1)){
            case 1:
                Header.setImageResource(R.drawable.piernagym1_2);
                Titulo.setText("Pierna completa");
                break;
            case  2:
                Header.setImageResource(R.drawable.gym_pectorales_hombre_1);
                Titulo.setText("Pectorales");
                break;
            case 3:
                Header.setImageResource(R.drawable.gym_hombro_hombre_1);
                Titulo.setText("Hombros y abdomen");
                break;
            case 4:
                Header.setImageResource(R.drawable.gym_espalda_hombre_1);
                Titulo.setText("Espalda");
                break;
            case 5:
                Header.setImageResource(R.drawable.gym_brazo_hombre_1);
                Titulo.setText("Brazo completo");
                break;

        }

        Regresar = findViewById(R.id.button4);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuprincipal = new Intent(RutinasGymEjercicios.this, MainActivity.class);
                RutinasGymEjercicios.this.startActivity(menuprincipal);
            }
        });

        Ejer1 = findViewById(R.id.cardGymEjer2);
        Ejer2 = findViewById(R.id.cardGymEjer3);
        Ejer3 = findViewById(R.id.cardGymEjer4);
        Ejer4 = findViewById(R.id.cardGymEjer5);
        Ejer5 = findViewById(R.id.cardGymEjer6);

        Ejer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejercicioDetalle();
            }
        });
        Ejer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejercicioDetalle();
            }
        });
        Ejer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejercicioDetalle();
            }
        });
        Ejer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejercicioDetalle();
            }
        });
        Ejer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejercicioDetalle();
            }
        });

    }

    public void ejercicioDetalle (){
        Intent ejerciciodetalle = new Intent(RutinasGymEjercicios.this, RutinasGymEjercicioDetalle.class);
        RutinasGymEjercicios.this.startActivity(ejerciciodetalle);
    }
}
