package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Recetas extends AppCompatActivity {
    Button Regresar;
    CardView Receta1, Receta2, Receta3;
    TextView Titulo1, Titulo2, Titulo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recetas);
        Regresar = findViewById(R.id.button);
        Receta1 = findViewById(R.id.cardRecetas1);
        Receta2 = findViewById(R.id.cardRecetas2);
        Receta3 = findViewById(R.id.cardRecetas3);

        Titulo1 = findViewById(R.id.recetasTitulo1);
        Titulo2 = findViewById(R.id.recetasTitulo2);
        Titulo3 = findViewById(R.id.recetasTitulo3);

        //Aqui se deberia poblar los titulos y las imagenes dependiendo los calculos de dieta sobre altura, peso y tal vez edad
        //
        //

        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(Recetas.this, MainActivity.class);
                Recetas.this.startActivity(detalle);
            }
        });

        Receta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(Recetas.this, RecetaDetalle.class);
                detalle.putExtra("NombreReceta", Titulo1.getText().toString());
                Recetas.this.startActivity(detalle);
            }
        });

        Receta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(Recetas.this, RecetaDetalle.class);
                detalle.putExtra("NombreReceta", Titulo2.getText().toString());
                Recetas.this.startActivity(detalle);
            }
        });

        Receta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detalle = new Intent(Recetas.this, RecetaDetalle.class);
                detalle.putExtra("NombreReceta", Titulo3.getText().toString());
                Recetas.this.startActivity(detalle);
            }
        });

    }
}
