package com.example.gym;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Exito extends AppCompatActivity {
    ImageView Check;
    TextView Titulo, Toca;
    Intent intentRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exito);

        Check = findViewById(R.id.imgCheck);
        Titulo = findViewById(R.id.txtRExitoso);
        Toca = findViewById(R.id.txtToca);
        intentRegistro = getIntent();


        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedireccionMenu();
            }
        });

        Titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedireccionMenu();
            }
        });

        Toca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedireccionMenu();
            }
        });
    }

    public void RedireccionMenu(){
        if(intentRegistro.getBooleanExtra("UserPays", false)) {
            Intent goToPay = new Intent(this, Metodo.class);
            goToPay.putExtra("NombrePlan", intentRegistro.getStringExtra("NombrePlan"));
            goToPay.putExtra("TiempoPlan", intentRegistro.getStringExtra("TiempoPlan"));
            goToPay.putExtra("CostoPlan", intentRegistro.getStringExtra("CostoPlan"));
            goToPay.putExtra("DescPlan", intentRegistro.getStringExtra("DescPlan"));
            this.startActivity(goToPay);
            finish();
        } else {
            Intent intentMenu = new Intent(Exito.this, MainActivity.class);
            Exito.this.startActivity(intentMenu);
            finish();
        }
    }
}
