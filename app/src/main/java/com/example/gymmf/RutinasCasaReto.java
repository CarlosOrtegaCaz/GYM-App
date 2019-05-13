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

public class RutinasCasaReto extends AppCompatActivity {
//
    ImageView Header;
    CardView Card1, Card2,Card3,Card4, Card5;
    Intent intentIns;
    TextView Titulo;
    Button Regresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rutinas_casa_reto);


        Header = findViewById(R.id.imgCasaRetoHeader);
        Titulo = findViewById(R.id.txtTitleCard);

        intentIns = getIntent();

        switch (intentIns.getIntExtra("Reto", 1)){
            case 1:
                Header.setImageResource(R.drawable.rutinas_casa_reto1_1);
                Titulo.setText("Perder peso y mantenerte saludable");
                break;
            case  2:
                Header.setImageResource(R.drawable.rutina_casa_reto2_1);
                Titulo.setText("Glueteos y abdomen");
                break;
            case 3:
                Header.setImageResource(R.drawable.rutinas_casa_reto3);
                Titulo.setText("Fortalecer músculos");
                break;

        }

        Card1 = findViewById(R.id.cardRutinasCasa1);
        Card2 = findViewById(R.id.cardRutinasCasa2);
        Card3 = findViewById(R.id.cardRutinasCasa3);
        Card4 = findViewById(R.id.cardRutinasCasa4);
        Card5 = findViewById(R.id.cardRutinasCasa5);

        Card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RutinasCasaReto.this, RutinasCasaRetoDetalle.class);
                intent.putExtra("Detalle", 1);
                RutinasCasaReto.this.startActivity(intent);
            }
        });

        Card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RutinasCasaReto.this, RutinasCasaRetoDetalle.class);
                intent.putExtra("Detalle", 2);
                RutinasCasaReto.this.startActivity(intent);
            }
        });

        Card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RutinasCasaReto.this, RutinasCasaRetoDetalle.class);
                intent.putExtra("Detalle", 3);
                RutinasCasaReto.this.startActivity(intent);
            }
        });

        Card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RutinasCasaReto.this, RutinasCasaRetoDetalle.class);
                intent.putExtra("Detalle", 5); //intercambiado con card5
                RutinasCasaReto.this.startActivity(intent);
            }
        });

        Card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RutinasCasaReto.this, RutinasCasaRetoDetalle.class);
                intent.putExtra("Detalle", 4); //intercambiado con card4
                RutinasCasaReto.this.startActivity(intent);
            }
        });

        Regresar = findViewById(R.id.button7);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RutinasCasaReto.this, MainActivity.class);
                RutinasCasaReto.this.startActivity(intent);
            }
        });


    }
}
