package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class RutinasGym extends AppCompatActivity {
    Button Regresar;
    CardView Lunes, Martes, Miercoles, Jueves, Viernes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rutinas_gym);

        Regresar = findViewById(R.id.button3);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuprincipal = new Intent(RutinasGym.this, MainActivity.class);
                RutinasGym.this.startActivity(menuprincipal);

            }
        });

        Lunes = findViewById(R.id.cardGymLunes);
        Martes = findViewById(R.id.cardGymMartes);
        Miercoles = findViewById(R.id.cardGymMiercoles);
        Jueves = findViewById(R.id.cardGymJueves);
        Viernes = findViewById(R.id.cardGymViernes);

        Lunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoejer = new Intent(RutinasGym.this, RutinasGymEjercicios.class);
                gotoejer.putExtra("Dia", 1);
                RutinasGym.this.startActivity(gotoejer);
            }
        });

        Martes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoejer = new Intent(RutinasGym.this, RutinasGymEjercicios.class);
                gotoejer.putExtra("Dia", 2);
                RutinasGym.this.startActivity(gotoejer);
            }
        });

        Miercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoejer = new Intent(RutinasGym.this, RutinasGymEjercicios.class);
                gotoejer.putExtra("Dia", 3);
                RutinasGym.this.startActivity(gotoejer);
            }
        });

        Jueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoejer = new Intent(RutinasGym.this, RutinasGymEjercicios.class);
                gotoejer.putExtra("Dia", 4);
                RutinasGym.this.startActivity(gotoejer);
            }
        });

        Viernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoejer = new Intent(RutinasGym.this, RutinasGymEjercicios.class);
                gotoejer.putExtra("Dia", 5);
                RutinasGym.this.startActivity(gotoejer);
            }
        });



    }
}
