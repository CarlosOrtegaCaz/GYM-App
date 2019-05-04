package com.example.gymmf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Dietas extends AppCompatActivity {
    ListView ListaDietas;
    String [] diasSemana = new String[] {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dietas);

        ListaDietas = findViewById(R.id.listaDietas);
       // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, diasSemana);
        adapter = new ArrayAdapter<>(this, R.layout.list_black_dieta, R.id.txtListBlack, diasSemana);

        ListaDietas.setAdapter(adapter);

        ListaDietas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  ItemClicked item = parent.getItemAtPosition(position);
                String dia = (String) parent.getItemAtPosition(position);
                System.out.println("DIIAA " + dia);
                Intent diaSemana = new Intent(Dietas.this, Recetas.class);
                diaSemana.putExtra("Dia", dia);
                Dietas.this.startActivity(diaSemana);
            }

        });
    }
}
