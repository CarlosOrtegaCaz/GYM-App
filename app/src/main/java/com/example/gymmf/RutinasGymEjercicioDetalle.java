package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class RutinasGymEjercicioDetalle extends AppCompatActivity {

    Button Regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rutinas_gym_ejercicio_detalle);

        Regresar = findViewById(R.id.button5);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RutinasGymEjercicioDetalle.this, MainActivity.class);
                RutinasGymEjercicioDetalle.this.startActivity(intent);
            }
        });
    }
}
