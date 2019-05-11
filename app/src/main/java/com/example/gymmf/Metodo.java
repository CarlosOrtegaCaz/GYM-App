package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class Metodo extends AppCompatActivity {
    CardView Codigogym, DepBan, Paypal, Oxxo;
    ImageView VisaMaster;
    Button MetodoPlanes;
    Intent planIntent;
    String nombrePlan, tiempoPlan, costoPlan, descPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_metodo);

        Codigogym = findViewById(R.id.cardCodigoGYM);
        DepBan = findViewById(R.id.cardDepBan);
        Paypal = findViewById(R.id.cardPaypal);
        Oxxo = findViewById(R.id.cardOXXO);
        VisaMaster = findViewById(R.id.imgVisaMaster);
        MetodoPlanes = findViewById(R.id.btnMetodoPlanes);
        planIntent = getIntent();

        nombrePlan = planIntent.getStringExtra("NombrePlan");
        tiempoPlan = planIntent.getStringExtra("TiempoPlan");
        costoPlan = planIntent.getStringExtra("CostoPlan");
        descPlan = planIntent.getStringExtra("DescPlan");


        Codigogym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent codgym = new Intent(Metodo.this, FormularioCodigoGym.class);
                codgym.putExtra("Tipo", "CodGym");
                codgym.putExtra("NombrePlan", nombrePlan);
                codgym.putExtra("TiempoPlan", tiempoPlan);
                codgym.putExtra("CostoPlan", costoPlan);
                codgym.putExtra("DescPlan", descPlan);
                Metodo.this.startActivity(codgym);
            }
        });

        //DepBan y VisaMaster deben hacer lo mismo
        DepBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depban = new Intent(Metodo.this, FormularioPago.class);
                depban.putExtra("Tipo", "DepBan");
                depban.putExtra("NombrePlan", nombrePlan);
                depban.putExtra("TiempoPlan", tiempoPlan);
                depban.putExtra("CostoPlan", costoPlan);
                depban.putExtra("DescPlan", descPlan);
                Metodo.this.startActivity(depban);
            }
        });

        VisaMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depban = new Intent(Metodo.this, FormularioPago.class);
                depban.putExtra("Tipo", "DepBan");
                depban.putExtra("NombrePlan", nombrePlan);
                depban.putExtra("TiempoPlan", tiempoPlan);
                depban.putExtra("CostoPlan", costoPlan);
                depban.putExtra("DescPlan", descPlan);
                Metodo.this.startActivity(depban);
            }
        });

        Paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paypal = new Intent(Metodo.this, FormularioPaypal.class);
                paypal.putExtra("Tipo", "PayPal");
                paypal.putExtra("NombrePlan", nombrePlan);
                paypal.putExtra("TiempoPlan", tiempoPlan);
                paypal.putExtra("CostoPlan", costoPlan);
                paypal.putExtra("DescPlan", descPlan);
                Metodo.this.startActivity(paypal);
            }
        });

        Oxxo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oxxo = new Intent(Metodo.this, FormularioOxxo.class);
                oxxo.putExtra("Tipo", "OXXO");
                oxxo.putExtra("NombrePlan", nombrePlan);
                oxxo.putExtra("TiempoPlan", tiempoPlan);
                oxxo.putExtra("CostoPlan", costoPlan);
                oxxo.putExtra("DescPlan", descPlan);
                Metodo.this.startActivity(oxxo);
            }
        });

        MetodoPlanes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent metodoplanes = new Intent(Metodo.this, Planes.class);
                Metodo.this.startActivity(metodoplanes);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent goToPlan = new Intent(this, Planes.class);
        this.startActivity(goToPlan);
        finish();
    }
}
