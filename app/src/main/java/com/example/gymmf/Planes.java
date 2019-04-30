package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Planes extends AppCompatActivity {
    CardView plan1, plan2, p1,p2, p3, p4, p5;
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


        p1 = findViewById(R.id.cardP1);
        p2 = findViewById(R.id.cardP2);
        p3 = findViewById(R.id.cardP3);
        p4 = findViewById(R.id.cardP4);
        p5 = findViewById(R.id.cardP5);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMetodo = new Intent(Planes.this, Inscripcion.class);
                intentMetodo.putExtra("DemoDay", false);
                intentMetodo.putExtra("NombrePlan", "60+");
                intentMetodo.putExtra("TiempoPlan", "1 Mes");
                intentMetodo.putExtra("CostoPlan", "300");
                intentMetodo.putExtra("DescPlan", "60 y mas con 40% de descuento");
                Planes.this.startActivity(intentMetodo);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMetodo = new Intent(Planes.this, Inscripcion.class);
                intentMetodo.putExtra("DemoDay", false);
                intentMetodo.putExtra("NombrePlan", "Reto 3 meses");
                intentMetodo.putExtra("TiempoPlan", "3 Meses");
                intentMetodo.putExtra("CostoPlan", "1500");
                intentMetodo.putExtra("DescPlan", "Supera tus limites en tan solo tres meses");
                Planes.this.startActivity(intentMetodo);
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMetodo = new Intent(Planes.this, Inscripcion.class);
                intentMetodo.putExtra("DemoDay", false);
                intentMetodo.putExtra("NombrePlan", "Plan familiar");
                intentMetodo.putExtra("TiempoPlan", "1 Mes");
                intentMetodo.putExtra("CostoPlan", "549");
                intentMetodo.putExtra("DescPlan", "Ejercitate con toda tu familia y obtengan grandes descuentos para todos");
                Planes.this.startActivity(intentMetodo);
            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMetodo = new Intent(Planes.this, Inscripcion.class);
                intentMetodo.putExtra("DemoDay", false);
                intentMetodo.putExtra("NombrePlan", "¡Sin excusas!");
                intentMetodo.putExtra("TiempoPlan", "1 Mes");
                intentMetodo.putExtra("CostoPlan", "400");
                intentMetodo.putExtra("DescPlan", "Disiplina y esfuerso");
                Planes.this.startActivity(intentMetodo);
            }
        });

        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMetodo = new Intent(Planes.this, Inscripcion.class);
                intentMetodo.putExtra("DemoDay", false);
                intentMetodo.putExtra("NombrePlan", "All Access");
                intentMetodo.putExtra("TiempoPlan", "Tiempo1");
                intentMetodo.putExtra("CostoPlan", "599");
                intentMetodo.putExtra("DescPlan", "¡Todo disponible!");
                Planes.this.startActivity(intentMetodo);
            }
        });

    }
}
