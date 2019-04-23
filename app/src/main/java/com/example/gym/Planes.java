package com.example.gym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Planes extends AppCompatActivity {
    CardView plan1, plan2;
    //  Dia de prueba, otros planes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planes);

        plan1 = findViewById(R.id.card_view);
        plan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistro = new Intent(Planes.this, Inscripcion.class);
                intentRegistro.putExtra("DemoDay", true);
                Planes.this.startActivity(intentRegistro);
            }
        });

        plan2 = findViewById(R.id.card_view2);
        plan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMetodo = new Intent(Planes.this, Inscripcion.class);
                intentMetodo.putExtra("DemoDay", false);
                intentMetodo.putExtra("NombrePlan", "Nombre1");
                intentMetodo.putExtra("TiempoPlan", "Tiempo1");
                intentMetodo.putExtra("CostoPlan", "500");
                intentMetodo.putExtra("DescPlan", "Desc1");
                Planes.this.startActivity(intentMetodo);
            }
        });

    }
}
