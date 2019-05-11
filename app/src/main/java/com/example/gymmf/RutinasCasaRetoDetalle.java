package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RutinasCasaRetoDetalle extends AppCompatActivity {
    ImageView Header;
    TextView Titulo, Instrucciones;
    Button Regresar;
    Intent intentIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rutinas_casa_reto_detalle);

        Header = findViewById(R.id.imgCasaRetoHeader);
        Titulo = findViewById(R.id.txtTitleCard);
        Instrucciones =findViewById(R.id.txtDescCard);

        intentIns = getIntent();

        switch (intentIns.getIntExtra("Detalle", 1)){
            case 1:
                Header.setImageResource(R.drawable.saltos_tijera);
                Titulo.setText("Saltos Jack");
                Instrucciones.setText(R.string.instrucciones_saltos_jacks);
                break;
            case  2:
                Header.setImageResource(R.drawable.sentadilla);
                Titulo.setText("Sentadillas");
                Instrucciones.setText(R.string.instrucciones_sentadillas);
                break;
            case 3:
                Header.setImageResource(R.drawable.plancha_pierna_1);
                Titulo.setText("Elevación de pierna");
                Instrucciones.setText(R.string.instrucciones_plancha_piernas);
                break;

            case 4:
                Header.setImageResource(R.drawable.postura_cobra_1);
                Titulo.setText("Postura de la cobra");
                Instrucciones.setText(R.string.instrucciones_posicion_cobra);
                break;

        }


    }
}
