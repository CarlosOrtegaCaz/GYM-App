package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class RutinasCasa extends AppCompatActivity {
    Button Reggresar;
    CardView Card1, Card2, Card3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rutinas_casa);

        Reggresar = findViewById(R.id.button6);
        Reggresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(RutinasCasa.this, MainActivity.class);
                RutinasCasa.this.startActivity(menu);
            }
        });

        Card1 = findViewById(R.id.cardRutinasCasa1);
        Card2 = findViewById(R.id.cardRutinasCasa2);
        Card3 = findViewById(R.id.cardRutinasCasa3);

        Card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reto1 = new Intent(RutinasCasa.this, RutinasCasaReto.class);
                reto1.putExtra("Reto", 1);
                RutinasCasa.this.startActivity(reto1);
            }
        });

        Card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reto2 = new Intent(RutinasCasa.this, RutinasCasaReto.class);
                reto2.putExtra("Reto", 2);
                RutinasCasa.this.startActivity(reto2);
            }
        });

        Card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reto3 = new Intent(RutinasCasa.this, RutinasCasaReto.class);
                reto3.putExtra("Reto", 3);
                RutinasCasa.this.startActivity(reto3);
            }
        });
    }

    public void selectedCard () {
        Intent reto = new Intent(RutinasCasa.this, RutinasCasaReto.class);
        RutinasCasa.this.startActivity(reto);
    }
}
